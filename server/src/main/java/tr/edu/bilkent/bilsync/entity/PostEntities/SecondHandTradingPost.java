package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "second_hand_trading_post")
public class SecondHandTradingPost extends TradingPost {
    public SecondHandTradingPost() {}
}
