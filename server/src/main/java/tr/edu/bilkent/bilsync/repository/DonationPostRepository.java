package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.DonationPostEntity;

@Repository
public interface DonationPostRepository extends CrudRepository<DonationPostEntity, Long> {
    DonationPostEntity findById(long id);
}
