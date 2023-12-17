package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents a vote entity for posts, storing information about the voter, post, and vote value.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
public class Vote {
    private long voterID;

    private int voteValue;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long postID;

    public Vote() {}

    public Vote(long postID, long voterID, int voteValue) {
        this.postID = postID;
        this.voterID = voterID;
        this.voteValue = voteValue;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public long getVoterID() {
        return voterID;
    }

    // Setter for voterID
    public void setVoterID(long voterID) {
        this.voterID = voterID;
    }

    // Getter for voteValue
    public int getVoteValue() {
        return voteValue;
    }

    // Setter for voteValue
    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }

    // Getter for postID
    public long getPostID() {
        return postID;
    }

    // Setter for postID
    public void setPostID(long postID) {
        this.postID = postID;
    }
}
