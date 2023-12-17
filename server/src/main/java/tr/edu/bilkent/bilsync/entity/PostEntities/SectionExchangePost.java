package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a section exchange post entity, extending the TradingPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "section_exchange_post")
public class SectionExchangePost extends TradingPost {
    public SectionExchangePost() {}
}
