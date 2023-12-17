package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.*;

/**
 * Represents a trading post entity, serving as the base class for posts involving exchanges.
 * This class is annotated with JPA annotations for entity mapping and uses the TABLE_PER_CLASS inheritance strategy.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class TradingPost extends Post {

    @Column(nullable = false)
    private long giverID;

    @Column(nullable = true)
    private long takerID;

    @Column(nullable = false)
    private boolean isResolved;

    @Column(nullable = false)
    private boolean isHeld;

    // Getters and Setters

    public long getGiverID() {
        return giverID;
    }

    public void setGiverID(long giverID) {
        this.giverID = giverID;
    }

    public long getTakerID() {
        return takerID;
    }

    public void setTakerID(long takerID) {
        this.takerID = takerID;
    }

    public boolean isResolved() {
        return isResolved;
    }

    public void setIsResolved(boolean resolved) {
        isResolved = resolved;
    }

    public boolean getIsHeld() {
        return isHeld;
    }

    public void setIsHeld(boolean held) {
        isHeld = held;
    }

    /**
     * Executes pre-persistence operations specific to SecondHandTradingPost before saving the entity to the database.
     * Initializes takerID, isResolved, and isHeld.
     * Invokes the prePersist method from the superclass (Post) for common pre-persistence operations.
     */
    @Override
    @PrePersist
    public void prePersist() {
        super.prePersist();
        this.takerID = -1;
        this.isResolved = false;
        this.isHeld = false;
    }
}
