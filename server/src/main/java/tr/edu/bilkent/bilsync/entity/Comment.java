package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code Comment} class represents a user-generated comment on a post or another comment.
 * It includes information about the author, content, likes, replies, and other relevant details.
 * This class is mapped to a database table for persistent storage using JPA annotations.
 */
@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "comment_sequence")
    @TableGenerator(name = "comment_sequence", table = "comment_sequence_table", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private long authorID;

    @ElementCollection
    @CollectionTable(name = "comment_reply", joinColumns = @JoinColumn(name = "comment_id"))
    @Column(name = "reply_id")
    private Set<Long> commentReplyList = new HashSet<>();

    @Column(nullable = false)
    private boolean isReply;

    @Column(nullable = false)
    private long likeCount;

    @OneToMany(targetEntity = UserEntity.class)
    private Set<UserEntity> taggedUserList = new HashSet<>();

    @Column(length = 1000, nullable = false)
    private String text;

    @Column(nullable = false)
    private Timestamp publishDate;

    @Column
    private long primaryCommentID;

    @Column(nullable = false)
    private long primaryPostID;

    public Comment() {}

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }

    public Set<Long> getCommentReplyList() {
        return commentReplyList;
    }

    public void setCommentReplyList(Set<Long> commentReplyList) {
        this.commentReplyList = commentReplyList;
    }

    public boolean getIsReply() {
        return isReply;
    }

    public void setIsReply(boolean reply) {
        isReply = reply;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public Set<UserEntity> getTaggedUserList() {
        return taggedUserList;
    }

    public void setTaggedUserList(Set<UserEntity> taggedUserList) { this.taggedUserList = taggedUserList; }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPrimaryCommentID() {
        return primaryCommentID;
    }

    public void setPrimaryCommentID(long primaryCommentID) {
        this.primaryCommentID = primaryCommentID;
    }

    public long getPrimaryPostID() { return primaryPostID; }

    public void setPrimaryPostID(long primaryPostID) { this.primaryPostID = primaryPostID; }

    /**
     * Executed before the entity is persisted to the database.
     * Initializes default values such as likeCount, commentReplyList, and publishDate.
     */
    @PrePersist
    public void prePersist() {
        this.likeCount = 0;
        this.commentReplyList = new HashSet<>();

        Instant instant = Instant.now();
        Instant trClock = instant.plus(Duration.ofHours(3));
        this.publishDate = Timestamp.from(trClock);
    }

    /**
     * Adds a comment ID to the list of comment replies associated with this comment.
     *
     * @param id The ID of the comment to be added to the list.
     */
    public void addComment(long id) { this.commentReplyList.add(id); }
}
