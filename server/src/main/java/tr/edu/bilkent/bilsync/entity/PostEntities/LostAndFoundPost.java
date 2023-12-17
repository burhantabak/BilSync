package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a lost and found post entity, extending the TradingPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "lost_and_found_post")
public class LostAndFoundPost extends TradingPost {
    public LostAndFoundPost() {}
}