package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.BorrowAndLendPost;
import tr.edu.bilkent.bilsync.entity.LostAndFoundPost;

import java.util.List;

@Repository
public interface LostAndFoundPostRepository extends CrudRepository<LostAndFoundPost, Long> {
    LostAndFoundPost findById(long id);
    @Query("SELECT p FROM LostAndFoundPost p ORDER BY p.postDate DESC")
    List<LostAndFoundPost> findLostAndFoundPostsSortedByDate();
    void deleteById(long id);
}
