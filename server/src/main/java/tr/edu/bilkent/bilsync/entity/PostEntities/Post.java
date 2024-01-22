package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.*;
import tr.edu.bilkent.bilsync.entity.Comment;

import java.sql.Timestamp;
import java.time.*;
import java.util.*;

/**
 * Represents a generic post entity, serving as the base class for various post types.
 * This class is annotated with JPA annotations for entity mapping and uses the TABLE_PER_CLASS inheritance strategy.
 */
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

    @Column(nullable = false)
    private String authorName;

    @Column(length = 3000, nullable = false)
    private String description;

    @Column(nullable = false)
    private String imageName = "";

    @Column(nullable = false)
    private long votes = 0;

    @OneToMany
    @Column(name = "listVotes")
    private List<Vote> listVotes = new ArrayList<>();

    @Column(nullable = false)
    private long views = 0;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER)
    private Set<Comment> commentList = new HashSet<>();

    @ElementCollection
    @Column(name = "hashtag")
    private Set<String> hashtagList;

    @Column(nullable = false)
    private boolean isAnonymous = false;

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

    /**
     * Default constructor for Post.
     */
    public Post() {
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id; }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthorID() {
        return authorID;
    }

    public String getAuthorName() { return authorName;}
    public void setAuthorName (String authorName) {
        this.authorName = authorName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imagePath) { this.imageName = imagePath; }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public List<Vote> getListVotes() {
        return listVotes;
    }

    public void setListVotes(List<Vote> voters) {
        this.listVotes = voters;
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

    public Set<String> getHashtagList() {
        return hashtagList;
    }

    public void setHashtagList(Set<String> hashtagList) {
        this.hashtagList = hashtagList;
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

    /**
     * Executes pre-persistence operations before saving the entity to the database.
     * Initializes votes, views, and commentList. Sets the postDate to the current time in the Turkish time zone.
     */
    public void prePersist() {
        this.votes = 0;
        this.views = 0;
        this.commentList = new HashSet<>();

        Instant instant = Instant.now();
        Instant trClock = instant.plus(Duration.ofHours(3));
        this.postDate = Timestamp.from(trClock);
    }

    /**
     * Adds a comment to the commentList associated with this post.
     *
     * @param comment The comment to be added.
     */
    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
}