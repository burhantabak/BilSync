package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.SectionExchangePost;

@Repository
public interface SectionExchangePostRepository extends CrudRepository<SectionExchangePost, Long> {
    SectionExchangePost findById(long id);
}
