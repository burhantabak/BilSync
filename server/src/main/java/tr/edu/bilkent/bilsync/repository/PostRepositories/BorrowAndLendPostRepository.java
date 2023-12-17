package tr.edu.bilkent.bilsync.repository.PostRepositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.PostEntities.BorrowAndLendPost;

import java.util.List;

/**
 * Repository interface for BorrowAndLendPost entities, extending CrudRepository.
 * Provides CRUD operations for BorrowAndLendPost entities and additional custom queries.
 */
@Repository
public interface BorrowAndLendPostRepository extends CrudRepository<BorrowAndLendPost, Long> {

    /**
     * Retrieves a borrow and lend post by its ID.
     *
     * @param id The ID of the borrow and lend post to retrieve.
     * @return The borrow and lend post with the specified ID.
     */
    BorrowAndLendPost findById(long id);

    /**
     * Retrieves a list of borrow and lend posts sorted by date in descending order.
     *
     * @return A list of borrow and lend posts sorted by date.
     */
    @Query("SELECT p FROM BorrowAndLendPost p ORDER BY p.postDate DESC")
    List<BorrowAndLendPost> findBorrowAndLendPostsSortedByDate();

    /**
     * Deletes a borrow and lend post by its ID.
     *
     * @param id The ID of the borrow and lend post to delete.
     */
    void deleteById(long id);
}