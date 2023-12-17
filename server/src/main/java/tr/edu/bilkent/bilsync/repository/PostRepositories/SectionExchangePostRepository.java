package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.SectionExchangePost;

import java.util.List;

/**
 * Repository interface for managing SectionExchangePost entities.
 * It extends CrudRepository for basic CRUD operations and includes custom query methods.
 */
@Repository
public interface SectionExchangePostRepository extends CrudRepository<SectionExchangePost, Long> {

    /**
     * Retrieves a SectionExchangePost by its ID.
     *
     * @param id The ID of the SectionExchangePost to retrieve.
     * @return The SectionExchangePost with the specified ID.
     */
    SectionExchangePost findById(long id);

    /**
     * Retrieves a list of SectionExchangePosts sorted by date in descending order.
     *
     * @return A list of SectionExchangePosts sorted by date.
     */
    @Query("SELECT p FROM SectionExchangePost p ORDER BY p.postDate DESC")
    List<SectionExchangePost> findSectionExchangePostsSortedByDate();

    /**
     * Deletes a SectionExchangePost by its ID.
     *
     * @param id The ID of the SectionExchangePost to delete.
     */
    void deleteById(long id);
}
