package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents an announcement post entity, extending the ForumPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "announcement_post")
public class AnnouncementPost extends ForumPost {
    public AnnouncementPost() {}
}