package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.*;
import tr.edu.bilkent.bilsync.entity.Comment;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

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
    private String imagePath = "OUR_DEFAULT_IMAGE_PATH";

    @Column(nullable = false)
    private long votes = 0;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "voters", joinColumns = @JoinColumn(name = "post_id"))
    @MapKeyColumn(name = "user_id")
    @Column(name = "vote")
    private Map<Long, Integer> voters = new HashMap<>();

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

    // Constructors
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

    public void setAuthorID(long authorID) {
        this.authorID = authorID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public long getVotes() {
        return votes;
    }

    public void setVotes(long votes) {
        this.votes = votes;
    }

    public Map<Long, Integer> getVoters() {
        return voters;
    }

    public void setVoters(Map<Long, Integer> voters) {
        this.voters = voters;
    }

    public void vote(long userId, int vote) { this.voters.put(userId, vote); }

    public void removeVote(long userId) { this.voters.remove(userId); }

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

    public void prePersist() {
        this.votes = 0;
        this.views = 0;
        this.commentList = new HashSet<>();

        Instant instant = Instant.now();
        Instant trClock = instant.plus(Duration.ofHours(3));
        this.postDate = Timestamp.from(trClock);
    }

    public void addComment(Comment comment) {
        this.commentList.add(comment);
    }
}