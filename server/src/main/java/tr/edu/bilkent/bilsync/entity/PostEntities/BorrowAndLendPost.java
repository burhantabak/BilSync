package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;

/**
 * Represents a borrow and lend post entity, extending the TradingPost class.
 * This class is annotated with JPA annotations for entity mapping.
 */
@Entity
@Table(name = "borrow_and_lend_post")
public class BorrowAndLendPost extends TradingPost {
    @Column(nullable = false)
    private Timestamp beginDate;

    @Column(nullable = false)
    private Timestamp endDate;

    /**
     * Default constructor for BorrowAndLendPost.
     */
    public BorrowAndLendPost() {}

    public Timestamp getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Timestamp beginDate) {
        this.beginDate = beginDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
}