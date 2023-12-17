package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a donation post entity, extending the TradingPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "donation_post")
public class DonationPost extends TradingPost {
    public DonationPost() {}
}
