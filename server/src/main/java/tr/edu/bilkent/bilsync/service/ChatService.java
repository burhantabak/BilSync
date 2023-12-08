package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.entity.Chat;
import tr.edu.bilkent.bilsync.entity.ChatUser;
import tr.edu.bilkent.bilsync.entity.ChatUserStatus;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.ChatRepository;
import tr.edu.bilkent.bilsync.repository.UserRepository;

import java.util.List;

@Service
public class ChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public ChatService(UserRepository userRepository, ChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
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
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
            addUserToChat(chat, user, ChatUserStatus.PENDING_REQUEST);
        }
    }

    private static void addUserToChat(Chat chat, UserEntity currentUser, ChatUserStatus status) {
        ChatUser chatUser = new ChatUser();
        chatUser.setChat(chat);
        chatUser.setUser(currentUser);
        chatUser.setStatus(status);
        chat.getUsers().add(chatUser);
    }

    public List<Chat> getChatsByUser(UserEntity user) {
        return chatRepository.findChatsByUsersContaining(user);
    }

    public List<Chat> getChatsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        return getChatsByUser(user);
    }
}
