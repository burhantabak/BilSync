package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.DonationPost;

import java.util.List;

@Repository
public interface DonationPostRepository extends CrudRepository<DonationPost, Long> {
    DonationPost findById(long id);
    @Query("SELECT p FROM DonationPost p ORDER BY p.postDate DESC")
    List<DonationPost> findDonationPostsSortedByDate();
    void deleteById(long id);
}
