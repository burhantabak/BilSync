package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.AnnouncementPost;
import tr.edu.bilkent.bilsync.entity.BorrowAndLendPost;

import java.util.List;

@Repository
public interface BorrowAndLendPostRepository extends CrudRepository<BorrowAndLendPost, Long> {
    BorrowAndLendPost findById(long id);
    @Query("SELECT p FROM BorrowAndLendPost p ORDER BY p.postDate DESC")
    List<BorrowAndLendPost> findBorrowAndLendPostsSortedByDate();
    void deleteById(long id);
}