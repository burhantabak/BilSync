package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private long authorID;

    @Column(length = 3000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageID;

    @Column(nullable = false)
    private long votes = 0;

    @Column(nullable = false)
    private long views = 0;

    @OneToMany(targetEntity = CommentEntity.class)
    private Set<CommentEntity> commentList = new HashSet<>();

    @Column(nullable = false)
    private String taggedUserListID;

    @Column(nullable = false)
    private String hashtagListID;

    @Column(nullable = false)
    private boolean isAnonymous;

    @Column(nullable = false)
    private String postDate;

    // Constructors
    public PostEntity() {
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public Set<CommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(Set<CommentEntity> commentList) {
        this.commentList = commentList;
    }

    public String getTaggedUserListID() {
        return taggedUserListID;
    }

    public void setTaggedUserListID(String taggedUserListID) {
        this.taggedUserListID = taggedUserListID;
    }

    public String getHashtagListID() {
        return hashtagListID;
    }

    public void setHashtagListID(String hashtagListID) {
        this.hashtagListID = hashtagListID;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }
}