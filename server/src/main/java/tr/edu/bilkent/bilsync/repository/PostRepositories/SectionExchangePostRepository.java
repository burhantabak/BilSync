package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.SectionExchangePost;

import java.util.List;

@Repository
public interface SectionExchangePostRepository extends CrudRepository<SectionExchangePost, Long> {
    SectionExchangePost findById(long id);
    @Query("SELECT p FROM SectionExchangePost p ORDER BY p.postDate DESC")
    List<SectionExchangePost> findSectionExchangePostsSortedByDate();
    void deleteById(long id);
}