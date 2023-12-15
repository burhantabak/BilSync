package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "donation_post")
public class DonationPost extends TradingPost {
    public DonationPost() {}
}
