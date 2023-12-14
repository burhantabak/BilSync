package tr.edu.bilkent.bilsync.dto;

import java.util.Date;

public class ChatMessageDto {
    private Long messageId;

    private Long senderId;

    private Long chatId;

    private Date date;

    private String body;

    private String image; // todo to be changed

    public ChatMessageDto(Long messageId, Long senderId, Long chatId, Date date, String body, String image) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.chatId = chatId;
        this.date = date;
        this.body = body;
        this.image = image;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
