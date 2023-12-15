package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "lost_and_found_post")
public class LostAndFoundPost extends TradingPost {
    public LostAndFoundPost() {}
}