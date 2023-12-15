package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.*;


@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class ForumPost extends Post {
    public ForumPost() {}

    @PrePersist
    public void prePersist() {
        super.prePersist();
    }
}
