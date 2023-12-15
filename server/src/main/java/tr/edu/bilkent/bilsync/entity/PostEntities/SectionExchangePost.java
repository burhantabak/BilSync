package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "section_exchange_post")
public class SectionExchangePost extends TradingPost {
    public SectionExchangePost() {}
}
