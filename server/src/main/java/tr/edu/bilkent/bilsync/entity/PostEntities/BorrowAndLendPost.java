package tr.edu.bilkent.bilsync.entity.PostEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrow_and_lend_post")
public class BorrowAndLendPost extends TradingPost {
    public BorrowAndLendPost() {}
}