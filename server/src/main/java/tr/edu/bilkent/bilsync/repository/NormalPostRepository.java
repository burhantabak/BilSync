package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.NormalPost;

@Repository
public interface NormalPostRepository extends CrudRepository<NormalPost, Long> {
    NormalPost findById(long id);
}
