package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.DonationPost;

@Repository
public interface DonationPostRepository extends CrudRepository<DonationPost, Long> {
    DonationPost findById(long id);
}
