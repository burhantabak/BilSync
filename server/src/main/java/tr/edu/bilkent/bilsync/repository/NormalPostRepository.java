package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.NormalPostEntity;

@Repository
public interface NormalPostRepository extends CrudRepository<NormalPostEntity, Long> {
    NormalPostEntity findById(long id);
}
