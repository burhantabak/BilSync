package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_donation_post")
public class DonationPostEntity extends TradingPostEntity {
    @Column(nullable = false)
    private long donorID;

    @Column(nullable = false)
    private long receiverID;

    public DonationPostEntity() {}
}
