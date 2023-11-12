package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private long authorID;

    @OneToMany(targetEntity = CommentEntity.class)
    private Set<CommentEntity> commentReplyList = new HashSet<>();

    @Column(nullable = false)
    private boolean isReply;

    @Column(nullable = false)
    private long likeCount = 0;

    @OneToMany(targetEntity = UserEntity.class)
    private Set<UserEntity> taggedUserList = new HashSet<>();

    @Column(length = 1000, nullable = false)
    private String text;

    @Column(nullable = false)
    private String publishDate;

    public long getAuthorID() {
        return authorID;
    }

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }

    public Set<CommentEntity> getCommentReplyList() {
        return commentReplyList;
    }

    public void setCommentReplyList(Set<CommentEntity> commentReplyList) {
        this.commentReplyList = commentReplyList;
    }

    public boolean isReply() {
        return isReply;
    }

    public void setReply(boolean reply) {
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

    public void setTaggedUserList(Set<UserEntity> taggedUserList) {
        this.taggedUserList = taggedUserList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
