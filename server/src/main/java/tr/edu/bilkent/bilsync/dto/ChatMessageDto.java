package tr.edu.bilkent.bilsync.dto;

import tr.edu.bilkent.bilsync.entity.ChatMessage;
import tr.edu.bilkent.bilsync.entity.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

/**
 * Data Transfer Object (DTO) representing a chat message. Used for transferring chat message information between the server and the client.
 */
public class ChatMessageDto {
    private Long messageId;

    private Long senderId;

    private Long chatId;

    private Date date;

    private String body;

    private byte[] image;

    /**
     * Constructor for creating a ChatMessageDto with specified values.
     *
     * @param messageId The unique identifier of the chat message.
     * @param senderId  The ID of the user who sent the message.
     * @param chatId    The ID of the chat associated with the message.
     * @param date      The date and time when the message was sent.
     * @param body      The content of the message.
     * @param image     The image associated with the message as a byte array.
     */
    public ChatMessageDto(Long messageId, Long senderId, Long chatId, Date date, String body, byte[] image) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.chatId = chatId;
        this.date = date;
        this.body = body;
        this.image = image;
    }

    /**
     * Default constructor for creating an empty ChatMessageDto.
     */
    public ChatMessageDto() {
    }

    /**
     * Constructor for creating a ChatMessageDto with specified values.
     *
     * @param messageId The unique identifier of the chat message.
     * @param senderId  The ID of the user who sent the message.
     * @param chatId    The ID of the chat associated with the message.
     * @param date      The date and time when the message was sent.
     * @param body      The content of the message.
     */
    public ChatMessageDto(Long messageId, Long senderId, Long chatId, Date date, String body) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.chatId = chatId;
        this.date = date;
        this.body = body;
    }

    /**
     * Constructor for creating a ChatMessageDto from a ChatMessage entity.
     *
     * @param chatMessage The ChatMessage entity from which to create the ChatMessageDto.
     */
    public ChatMessageDto(ChatMessage chatMessage) {
        this(chatMessage.getId(), chatMessage.getSender().getId(), chatMessage.getChat().getId(), chatMessage.getDate(), chatMessage.getBody());
        Image i = chatMessage.getImage();
        if (i == null) {
            this.image = null;
        } else {
            try {
                this.image = Files.readAllBytes(Paths.get(i.getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
