package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.dto.ChatMessageDto;
import tr.edu.bilkent.bilsync.entity.*;
import tr.edu.bilkent.bilsync.repository.ChatMessageRepository;
import tr.edu.bilkent.bilsync.repository.ChatRepository;
import tr.edu.bilkent.bilsync.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    private final ChatMessageRepository chatMessageRepository;

    public ChatService(UserRepository userRepository, ChatRepository chatRepository, ChatMessageRepository chatMessageRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    public void createChat(ChatDto chatDto, UserEntity currentUser) {
        List<Long> userIds = chatDto.getUserIds();
        if (!chatDto.isGroupChat() && userIds.size() != 1) {
            throw new IllegalArgumentException("Cannot create private chat with >1 members");
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

    private void addUsersToChat(Chat chat, List<Long> userIds) {
        for (long userId : userIds) {
            UserEntity user = userRepository.findById(userId);
            if (user == null) {
                throw new RuntimeException("User not found.");
            }

            addUserToChat(chat, user, ChatUserStatus.PENDING_REQUEST);
        }
    }

    private static void addUserToChat(Chat chat, UserEntity currentUser, ChatUserStatus status) {
        ChatUser chatUser = createChatUser(chat, currentUser, status);
        chat.getUsers().add(chatUser);
    }

    private static ChatUser createChatUser(Chat chat, UserEntity user, ChatUserStatus status) {
        ChatUser chatUser = new ChatUser();
        chatUser.setChat(chat);
        chatUser.setUser(user);
        chatUser.setStatus(status);
        return chatUser;
    }

    public List<Chat> getChatsByUser(UserEntity user) {
        return chatRepository.findChatsByUsersContaining(user);
    }

    public List<Chat> getChatsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        return getChatsByUser(user);
    }

    public void inviteUsers(Long chatId, List<Long> inviteeIds, UserEntity currentUser) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found."));
        ChatUser adminUser = chat.getUsers().stream()
                .filter(e -> e.getStatus() == ChatUserStatus.GROUP_ADMIN)
                .filter(e -> e.getUser().getId() == currentUser.getId())
                .findAny()
                .orElseThrow(RuntimeException::new); // if current user is not the admin of the group, an exc is thrown

        Set<Long> memberIds = chat.getUsers()
                .stream()
                .map(ChatUser::getUser)
                .map(UserEntity::getId)
                .collect(Collectors.toSet());

        for (Long userId : inviteeIds) {
            if (memberIds.contains(userId)) {
                throw new RuntimeException("Member " + userId + " already in group");
            }
        }
        inviteeIds
                .stream()
                .distinct()
                .map(userRepository::findById)
                .map(e -> e.orElseThrow(() -> new RuntimeException("User not found.")))
                .forEach(e -> addUserToChat(chat, e, ChatUserStatus.PENDING_REQUEST));
        chatRepository.save(chat);
    }

    public void sendMessageToChat(Long chatId, ChatMessageDto chatMessageDto, UserEntity currentUser) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new RuntimeException("Chat not found."));
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(currentUser);
        chatMessage.setBody(chatMessageDto.getBody());
        chatMessage.setImagePath(null); // todo to be changed later
        chatMessage.setDate(new Date());
        chatMessage.setChat(chat);
        chatMessageRepository.save(chatMessage);
    }
}
