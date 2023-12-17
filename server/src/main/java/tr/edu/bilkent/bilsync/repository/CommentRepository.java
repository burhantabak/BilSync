package tr.edu.bilkent.bilsync.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Comment;

/**
 * Repository interface for managing Comment entities.
 * It extends CrudRepository for basic CRUD operations.
 */
@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Retrieves a comment by its ID.
     *
     * @param id The ID of the comment to retrieve.
     * @return The comment with the specified ID.
     */
    Comment findById(long id);
}
