package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.NormalPost;

import java.util.List;

@Repository
public interface NormalPostRepository extends CrudRepository<NormalPost, Long> {
    NormalPost findById(long id);
    @Query("SELECT p FROM NormalPost p ORDER BY p.postDate DESC")
    List<NormalPost> findNormalPostsSortedByDate();
    void deleteById(long id);
}
