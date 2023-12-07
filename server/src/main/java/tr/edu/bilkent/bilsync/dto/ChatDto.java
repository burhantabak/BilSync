package tr.edu.bilkent.bilsync.dto;

import java.util.List;

public class ChatDto {
    private String chatName;
    private List<Long> userIds;

    public ChatDto(String chatName, List<Long> userIds) {
        this.chatName = chatName;
        this.userIds = userIds;
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
