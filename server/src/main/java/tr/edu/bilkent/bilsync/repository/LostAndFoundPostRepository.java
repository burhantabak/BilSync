package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.BorrowAndLendPost;
import tr.edu.bilkent.bilsync.entity.LostAndFoundPost;

@Repository
public interface LostAndFoundPostRepository extends CrudRepository<LostAndFoundPost, Long> {
    LostAndFoundPost findById(long id);
}
