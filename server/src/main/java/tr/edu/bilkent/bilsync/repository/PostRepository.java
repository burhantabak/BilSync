package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.NormalPostEntity;
import tr.edu.bilkent.bilsync.entity.PostEntity;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
    PostEntity findById(long id);
}
