package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.dto.ChatMessageDto;
import tr.edu.bilkent.bilsync.entity.*;
import tr.edu.bilkent.bilsync.repository.ChatMessageRepository;
import tr.edu.bilkent.bilsync.repository.ChatRepository;
import tr.edu.bilkent.bilsync.repository.ImageRepository;
import tr.edu.bilkent.bilsync.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing chat-related operations.
 */
@Service
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final ImageRepository imageRepository;

    /**
     * Constructor for ChatService, injecting the required repositories.
     *
     * @param userRepository         The repository for UserEntity entities.
     * @param chatRepository         The repository for Chat entities.
     * @param chatMessageRepository  The repository for ChatMessage entities.
     * @param imageRepository        The repository for Image entities.
     */
    public ChatService(UserRepository userRepository, ChatRepository chatRepository, ChatMessageRepository chatMessageRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.imageRepository = imageRepository;
    }

    /**
     * Retrieves chat messages by chat ID.
     *
     * @param chatId The ID of the chat.
     * @param currentUser The current user viewing the chat
     * @return List of ChatMessageDto objects.
     */
    public List<ChatMessageDto> getMessagesByChatId(Long chatId, UserEntity currentUser) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found."));
        if (!userHasAccessToChat(currentUser, chatId)){
            throw new IllegalArgumentException("You are not a participant of this chat");
        }
        List<ChatMessage> chatMessages = chat.getChatMessages();
        return chatMessages.stream().map(ChatMessageDto::new).toList();
    }

    /**
     * Creates a new chat based on the provided ChatDto and current user.
     *
     * @param chatDto      The ChatDto containing chat details.
     * @param currentUser  The current user creating the chat.
     */
    public void createChat(ChatDto chatDto, UserEntity currentUser) {
        List<Long> userIds = chatDto.getUserIds().stream().distinct().toList();
        if(userIds.stream().anyMatch(userId -> (userId == currentUser.getId()))){
            throw new IllegalArgumentException("You cannot add yourself to a chat. You are automatically added to a chat you create, already!");
        }
        if (!chatDto.isGroupChat()) {
            if (userIds.size() != 1){
                throw new IllegalArgumentException("Cannot create private chat with !=2 members");
            }
            UserEntity otherUser = userRepository.findById(userIds.get(0)).orElseThrow(() -> new IllegalArgumentException("User does not exist"));
            if (chatRepository.findChatsByUsersContaining(currentUser).stream().filter(Chat::isGroupChat).anyMatch(chat -> chat.getUsers().stream().anyMatch(user -> user.getId() == otherUser.getId()))){
                throw new IllegalArgumentException("Private chat with these users already exists!");
            }
        }
        String chatName = chatDto.getChatName();
        Chat chat = new Chat();
        chat.setGroupChat(chatDto.isGroupChat());
        addUsersToChat(chat, userIds);
        ChatUserStatus currentUserStatus;
        if (chatDto.isGroupChat()) {
            chat.setChatName(chatName);
            currentUserStatus = ChatUserStatus.GROUP_ADMIN;
        } else {
            currentUserStatus = ChatUserStatus.SUBSCRIBED;
        }
        addUserToChat(chat, currentUser, currentUserStatus);
        chatRepository.save(chat);
    }

    /**
     * Adds users to the chat with the specified IDs.
     *
     * @param chat      The chat to which users will be added.
     * @param userIds   The IDs of the users to be added.
     */
    private void addUsersToChat(Chat chat, List<Long> userIds) {
        for (long userId : userIds) {
            UserEntity user = userRepository.findById(userId);
            if (user == null) {
                throw new RuntimeException("User not found.");
            }

            addUserToChat(chat, user, ChatUserStatus.PENDING_REQUEST);
        }
    }

    /**
     * Adds a user to the chat with the specified status.
     *
     * @param chat      The chat to which the user will be added.
     * @param currentUser   The user to be added to the chat.
     * @param status    The status of the user in the chat.
     */
    private static void addUserToChat(Chat chat, UserEntity currentUser, ChatUserStatus status) {
        ChatUser chatUser = createChatUser(chat, currentUser, status);
        chat.getUsers().add(chatUser);
    }

    /**
     * Creates a ChatUser instance for the specified chat, user, and status.
     *
     * @param chat      The chat to which the user will be added.
     * @param user      The user to be added to the chat.
     * @param status    The status of the user in the chat.
     * @return The created ChatUser instance.
     */
    private static ChatUser createChatUser(Chat chat, UserEntity user, ChatUserStatus status) {
        ChatUser chatUser = new ChatUser();
        chatUser.setChat(chat);
        chatUser.setUser(user);
        chatUser.setStatus(status);
        return chatUser;
    }

    /**
     * Retrieves a list of chats associated with the specified user.
     *
     * @param user  The user for whom chats are retrieved.
     * @return List of Chat data transfer object entities.
     */
    public List<ChatDto> getChatsByUser(UserEntity user) {
        return chatRepository.findChatsByUsersContaining(user).stream().map(chat -> {
            ChatDto chatDto = new ChatDto(chat);
            Long otherUserId = chatDto.getUserIds().stream().filter(id -> id != user.getId()).findFirst().orElseThrow(() -> new RuntimeException("Invalid request"));
            UserEntity otherUser = userRepository.findById(otherUserId).orElseThrow(() -> new IllegalArgumentException("Invalid request"));
            chatDto.setChatName(otherUser.getName());
            return chatDto;
        }).toList();
    }

    /**
     * Invites users to the specified chat.
     *
     * @param chatId        The ID of the chat to which users will be invited.
     * @param inviteeIds    The IDs of the users to be invited.
     * @param currentUser   The user initiating the invitation.
     */
    public void inviteUsers(Long chatId, List<Long> inviteeIds, UserEntity currentUser) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new IllegalArgumentException("Chat not found."));

        ChatUser adminUser = chat.getUsers().stream()
                .filter(e -> e.getStatus() == ChatUserStatus.GROUP_ADMIN)
                .filter(e -> e.getUser().getId() == currentUser.getId())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("You are not the admin of this group"));

        Set<Long> memberIds = chat.getUsers()
                .stream()
                .map(ChatUser::getUser)
                .map(UserEntity::getId)
                .collect(Collectors.toSet());

        for (Long userId : inviteeIds) {
            if (memberIds.contains(userId)) {
                throw new IllegalArgumentException("At least one member is already in group");
            }
        }
        inviteeIds
                .stream()
                .distinct()
                .map(userRepository::findById)
                .map(e -> e.orElseThrow(() -> new IllegalArgumentException("User not found.")))
                .forEach(e -> addUserToChat(chat, e, ChatUserStatus.PENDING_REQUEST));
        chatRepository.save(chat);
    }

    /**
     * Sends a message to the specified chat.
     *
     * @param chatId            The ID of the chat to which the message will be sent.
     * @param chatMessageDto    The ChatMessageDto containing the message details.
     * @param currentUser       The user sending the message.
     * @return The ChatMessageDto of the sent message.
     */
    public ChatMessageDto sendMessageToChat(Long chatId, ChatMessageDto chatMessageDto, UserEntity currentUser) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found."));
        if (!userHasAccessToChat(currentUser, chat.getId())){
            throw new IllegalArgumentException("You are not a participant of this chat.");
        }
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(currentUser);
        chatMessage.setBody(chatMessageDto.getBody());
        chatMessage.setDate(new Date());
        chatMessage.setChat(chat);
        byte[] dtoImage = chatMessageDto.getImage();
        if (dtoImage != null) {
            Image image = imageRepository.save(new Image());
            try {
                Files.write(Paths.get(image.getPath()), dtoImage);
            } catch (IOException e) {
                imageRepository.delete(image);
                throw new RuntimeException(e);
            }
            chatMessage.setImage(image);
        }
        return new ChatMessageDto(chatMessageRepository.save(chatMessage));
    }

    /**
     * Checks if the specified user has access to the given chat.
     *
     * @param currentUser   The user for whom access is being checked.
     * @param chatId        The ID of the chat.
     * @return True if the user has access, false otherwise.
     */
    public boolean userHasAccessToChat(UserEntity currentUser, Long chatId) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found."));
        return chat.getUsers().stream().filter(chatUser -> Objects.equals(chatUser.getId(), currentUser.getId())).count() == 1;
    }
}
