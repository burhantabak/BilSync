package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

/**
 * Represents a user's participation in a chat, storing information about the associated chat, user, and user status.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
public class ChatUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private ChatUserStatus status;

    public ChatUserStatus getStatus() {
        return status;
    }

    public void setStatus(ChatUserStatus status) {
        this.status = status;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
