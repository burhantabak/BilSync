package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.*;

/**
 * Represents a forum post entity, serving as the base class for various post types.
 * This class is annotated with JPA annotations for entity mapping and uses the TABLE_PER_CLASS inheritance strategy.
 */
@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ForumPost extends Post {
    public ForumPost() {}

    /**
     * Executes pre-persistence operations before saving the entity to the database.
     * Invokes the prePersist method from the superclass for common pre-persistence operations.
     */
    @PrePersist
    public void prePersist() {
        super.prePersist();
    }
}
