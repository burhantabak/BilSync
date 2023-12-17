package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.DonationPost;

import java.util.List;

/**
 * Repository interface for DonationPost entities, extending CrudRepository.
 * Provides CRUD operations for DonationPost entities and additional custom queries.
 */
@Repository
public interface DonationPostRepository extends CrudRepository<DonationPost, Long> {

    /**
     * Retrieves a donation post by its ID.
     *
     * @param id The ID of the donation post to retrieve.
     * @return The donation post with the specified ID.
     */
    DonationPost findById(long id);

    /**
     * Retrieves a list of donation posts sorted by date in descending order.
     *
     * @return A list of donation posts sorted by date.
     */
    @Query("SELECT p FROM DonationPost p ORDER BY p.postDate DESC")
    List<DonationPost> findDonationPostsSortedByDate();

    /**
     * Deletes a donation post by its ID.
     *
     * @param id The ID of the donation post to delete.
     */
    void deleteById(long id);
}
