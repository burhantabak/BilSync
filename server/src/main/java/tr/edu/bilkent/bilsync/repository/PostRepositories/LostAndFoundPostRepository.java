package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.LostAndFoundPost;

import java.util.List;

/**
 * Repository interface for LostAndFoundPost entities, extending CrudRepository.
 * Provides CRUD operations for LostAndFoundPost entities and additional custom queries.
 */
@Repository
public interface LostAndFoundPostRepository extends CrudRepository<LostAndFoundPost, Long> {

    /**
     * Retrieves a lost and found post by its ID.
     *
     * @param id The ID of the lost and found post to retrieve.
     * @return The lost and found post with the specified ID.
     */
    LostAndFoundPost findById(long id);

    /**
     * Retrieves a list of lost and found posts sorted by date in descending order.
     *
     * @return A list of lost and found posts sorted by date.
     */
    @Query("SELECT p FROM LostAndFoundPost p ORDER BY p.postDate DESC")
    List<LostAndFoundPost> findLostAndFoundPostsSortedByDate();

    /**
     * Deletes a lost and found post by its ID.
     *
     * @param id The ID of the lost and found post to delete.
     */
    void deleteById(long id);
}
