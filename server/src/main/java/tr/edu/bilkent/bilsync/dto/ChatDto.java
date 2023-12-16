package tr.edu.bilkent.bilsync.dto;
import tr.edu.bilkent.bilsync.entity.Chat;
import tr.edu.bilkent.bilsync.entity.ChatUser;
import tr.edu.bilkent.bilsync.entity.UserEntity;

import java.util.List;

import static java.util.Optional.ofNullable;

public class ChatDto {
    private String chatName;
    private List<Long> userIds;

    private boolean isGroupChat;

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
    private Long chatId;

    public ChatDto(Chat chat) { // returns a chat with the name of other user if it is private
        this(chat.getChatName(),
                chat.getUsers().stream().map(ChatUser::getUser).map(UserEntity::getId).toList(),
                chat.getId(),
                chat.isGroupChat());
    }

    public ChatDto(String chatName, List<Long> userIds, Long chatId, boolean isGroupChat) {
        this.chatName = chatName;
        this.userIds = userIds;
        this.chatId = chatId;
        this.isGroupChat = isGroupChat;
    }
    public boolean isGroupChat() {
        return isGroupChat;
    }

    public void setGroupChat(boolean groupChat) {
        isGroupChat = groupChat;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public List<Long> getUserIds() {
        return userIds;
    }
}
