package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a second-hand trading post entity, extending the TradingPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "second_hand_trading_post")
public class SecondHandTradingPost extends TradingPost {

    @Column(nullable = true)
    private String IBAN;

    @Column(nullable = false)
    private double price;

    /**
     * Default constructor for SecondHandTradingPost.
     */
    public SecondHandTradingPost() {}

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) { this.IBAN = IBAN; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

}
