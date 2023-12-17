package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.NormalPost;

import java.util.List;

/**
 * Repository interface for NormalPost entities, extending CrudRepository.
 * Provides CRUD operations for NormalPost entities and additional custom queries.
 */
@Repository
public interface NormalPostRepository extends CrudRepository<NormalPost, Long> {

    /**
     * Retrieves a normal post by its ID.
     *
     * @param id The ID of the normal post to retrieve.
     * @return The normal post with the specified ID.
     */
    NormalPost findById(long id);

    /**
     * Retrieves a list of normal posts sorted by date in descending order.
     *
     * @return A list of normal posts sorted by date.
     */
    @Query("SELECT p FROM NormalPost p ORDER BY p.postDate DESC")
    List<NormalPost> findNormalPostsSortedByDate();

    /**
     * Deletes a normal post by its ID.
     *
     * @param id The ID of the normal post to delete.
     */
    void deleteById(long id);
}
