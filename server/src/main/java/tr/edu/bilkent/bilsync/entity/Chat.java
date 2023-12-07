package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Message> messages = new ArrayList<>(); // todo change to id

    @ManyToMany
    private List<UserEntity> users = new ArrayList<>(); // todo change to id

    @ManyToOne
    @JoinColumn(name = "group_admin_id")
    private UserEntity groupAdmin; // todo change to id

    private boolean isGroupChat;

    private String chatName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public UserEntity getGroupAdmin() {
        return groupAdmin;
    }

    public void setGroupAdmin(UserEntity groupAdmin) {
        this.groupAdmin = groupAdmin;
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

    public void addUser(UserEntity user){
        this.users.add(user);
    }
}
