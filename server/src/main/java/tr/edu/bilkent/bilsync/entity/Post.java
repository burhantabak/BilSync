package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "post_sequence")
    @TableGenerator(name = "post_sequence", table = "post_sequence_table", allocationSize = 1)
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

    @OneToMany(targetEntity = Comment.class)
    private Set<Comment> commentList = new HashSet<>();

    @Column(nullable = true)
    private String taggedUserListID;

    @Column(nullable = true)
    private String hashtagListID;

    @Column(nullable = false)
    private boolean isAnonymous;

    @Column(nullable = false)
    private Timestamp postDate;

    /*
    0 = AnnouncementPost
    1 = BorrowAndLendPost
    2 = DonationPost
    3 = LostAndFoundPost
    4 = NormalPost
    5 = SectionExchangePost
    6 = SecondHandTradingPost
    */
    @Column(nullable = false)
    private byte postType;

    // Constructors
    public Post() {
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

    public Set<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(Set<Comment> commentList) {
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

    public boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(boolean anonymous) {
        isAnonymous = anonymous;
    }

    public Timestamp getPostDate() { return postDate; }

    public byte getPostType() { return postType; }

    public void setPostType(byte postType) { this.postType = postType; }

    public void setPostDate(Timestamp postDate) { this.postDate = postDate; }

    public void prePersist() {
        this.votes = 0;
        this.views = 0;
        this.commentList = new HashSet<Comment>();
        // Get the current time in UTC+3
        OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneOffset.ofHours(3));
        // Convert OffsetDateTime to Timestamp
        this.postDate = Timestamp.from(offsetDateTime.toInstant());
    }
}