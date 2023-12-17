package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a normal forum post entity, extending the ForumPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "normal_post")
public class NormalPost extends ForumPost {
}
