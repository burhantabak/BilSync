package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.BorrowAndLendPost;

@Repository
public interface BorrowAndLendPostRepository extends CrudRepository<BorrowAndLendPost, Long> {
    BorrowAndLendPost findById(long id);
}