package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.*;


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
