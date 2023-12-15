package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "borrow_and_lend_post")
public class BorrowAndLendPost extends TradingPost {
    @Column(nullable = false)
    private Timestamp beginDate;

    @Column(nullable = false)
    private Timestamp endDate;

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