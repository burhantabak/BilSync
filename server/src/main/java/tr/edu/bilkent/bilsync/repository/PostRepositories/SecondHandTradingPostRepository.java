package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.SecondHandTradingPost;

import java.util.List;

/**
 * Repository interface for SecondHandTradingPost entities, extending CrudRepository.
 * Provides CRUD operations for SecondHandTradingPost entities and additional custom queries.
 */
@Repository
public interface SecondHandTradingPostRepository extends CrudRepository<SecondHandTradingPost, Long> {

    /**
     * Retrieves a second-hand trading post by its ID.
     *
     * @param id The ID of the second-hand trading post to retrieve.
     * @return The second-hand trading post with the specified ID.
     */
    SecondHandTradingPost findById(long id);

    /**
     * Retrieves a list of second-hand trading posts sorted by date in descending order.
     *
     * @return A list of second-hand trading posts sorted by date.
     */
    @Query("SELECT p FROM SecondHandTradingPost p ORDER BY p.postDate DESC")
    List<SecondHandTradingPost> findSecondHandTradingPostsSortedByDate();

    /**
     * Deletes a second-hand trading post by its ID.
     *
     * @param id The ID of the second-hand trading post to delete.
     */
    void deleteById(long id);
}