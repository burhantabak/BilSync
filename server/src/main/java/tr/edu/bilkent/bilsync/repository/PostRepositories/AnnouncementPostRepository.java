package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.AnnouncementPost;

import java.util.List;

/**
 * Repository interface for AnnouncementPost entities, extending CrudRepository.
 * Provides CRUD operations for AnnouncementPost entities and additional custom queries.
 */
@Repository
public interface AnnouncementPostRepository extends CrudRepository<AnnouncementPost, Long> {

    /**
     * Retrieves an announcement post by its ID.
     *
     * @param id The ID of the announcement post to retrieve.
     * @return The announcement post with the specified ID.
     */
    AnnouncementPost findById(long id);

    /**
     * Retrieves a list of announcement posts sorted by date in descending order.
     *
     * @return A list of announcement posts sorted by date.
     */
    @Query("SELECT p FROM AnnouncementPost p ORDER BY p.postDate DESC")
    List<AnnouncementPost> findAnnouncementPostsSortedByDate();

    /**
     * Deletes an announcement post by its ID.
     *
     * @param id The ID of the announcement post to delete.
     */
    void deleteById(long id);
}
