package tr.edu.bilkent.bilsync.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "app_donation_post")
public class DonationPostEntity extends PostEntity {
    public DonationPostEntity() {}
}
