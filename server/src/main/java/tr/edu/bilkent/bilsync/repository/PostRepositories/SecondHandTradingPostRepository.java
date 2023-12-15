package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.SecondHandTradingPost;

import java.util.List;

@Repository
public interface SecondHandTradingPostRepository extends CrudRepository<SecondHandTradingPost, Long> {
    SecondHandTradingPost findById(long id);
    @Query("SELECT p FROM SecondHandTradingPost p ORDER BY p.postDate DESC")
    List<SecondHandTradingPost> findSecondHandTradingPostsSortedByDate();
    void deleteById(long id);
}