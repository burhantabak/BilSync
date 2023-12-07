package tr.edu.bilkent.bilsync.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.ChatDto;
import tr.edu.bilkent.bilsync.entity.Chat;
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

    public void createChat(ChatDto chatDto) {
        createChat(chatDto.getChatName(), chatDto.getUserIds());
    }

    private void createChat(String chatName, List<Long> userIds) {
        Chat chat;
        if (userIds.size() <= 1) {
            throw new IllegalArgumentException();
        } else if (userIds.size() == 2) {
            chat = createPrivateChat(userIds);
        } else {
            chat = createGroupChat(chatName, userIds);
        }
        chatRepository.save(chat);
    }

    private Chat createGroupChat(String chatName, List<Long> userIds) {
        Chat chat = new Chat();
        chat.setChatName(chatName);
        chat.setGroupChat(true);
        saveUsersToChat(userIds, chat);
        UserEntity admin = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        chat.setGroupAdmin(admin);
        return chat;
    }

    private Chat createPrivateChat(List<Long> userIds) {
        Chat chat = new Chat();
        chat.setGroupChat(false);
        saveUsersToChat(userIds, chat);
        return chat;
    }

    private void saveUsersToChat(List<Long> userIds, Chat chat) {
        for(long userId : userIds){
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
            chat.addUser(user);
        }
    }

    public List<Chat> getChatsByUser(UserEntity user){
        return chatRepository.findChatsByUsersContaining(user);
    }

    public List<Chat> getChatsByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found."));
        return getChatsByUser(user);
    }
}
