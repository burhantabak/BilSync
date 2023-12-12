package tr.edu.bilkent.bilsync.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tr.edu.bilkent.bilsync.entity.Transaction;

/**
 * Repository interface for managing {@link Transaction} entities in the database.
 */
@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    /**
     * Retrieves a transaction by its taker's ID.
     *
     * @param id The ID of the taker.
     * @return The {@link Transaction} associated with the taker's ID, or {@code null} if not found.
     */
    Transaction findByTakerId(Long id);
}
