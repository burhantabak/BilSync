package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.SecondHandTradingPost;

@Repository
public interface SecondHandTradingPostRepository extends CrudRepository<SecondHandTradingPost, Long> {
    SecondHandTradingPost findById(long id);
}