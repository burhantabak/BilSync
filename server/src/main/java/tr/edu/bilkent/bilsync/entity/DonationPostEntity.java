package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "donation_post")
public class DonationPostEntity extends TradingPostEntity {
    public DonationPostEntity() {}
}
