package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private long authorID;

    @OneToMany(targetEntity = Comment.class)
    private Set<Comment> commentReplyList = new HashSet<>();

    @Column(nullable = false)
    private boolean isReply;

    @Column(nullable = false)
    private long likeCount = 0;

    @OneToMany(targetEntity = User.class)
    private Set<User> taggedUserList = new HashSet<>();

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

    public Set<Comment> getCommentReplyList() {
        return commentReplyList;
    }

    public void setCommentReplyList(Set<Comment> commentReplyList) {
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

    public Set<User> getTaggedUserList() {
        return taggedUserList;
    }

    public void setTaggedUserList(Set<User> taggedUserList) {
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

    // need help from DB team to correct these
    @ManyToMany(targetEntity = User.class)
    private Set<User> reporters = new HashSet<>();

    public Set<User> getReporters() {
        return reporters;
    }

    public void setReporters(Set<User> reporters) {
        this.reporters = reporters;
    }

}
