package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.entity.Transaction;
import tr.edu.bilkent.bilsync.repository.NormalPostRepository;
import tr.edu.bilkent.bilsync.repository.TransactionRepository;

import java.util.List;

/**
 * Service class for managing transactions.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    //todo this needs to be changed to trading post
    private final NormalPostRepository postRepository;

    /**
     * Constructs a new instance of the {@code TransactionService}.
     *
     * @param transactionRepository The repository for handling transactions.
     * @param postRepository        The repository for handling posts (assuming it exists).
     */
    public TransactionService(TransactionRepository transactionRepository, NormalPostRepository postRepository) {
        this.transactionRepository = transactionRepository;
        this.postRepository = postRepository;
    }

    /**
     * Retrieves all transactions.
     *
     * @return A list of all transactions.
     */
    public List<Transaction> getAllTransactions() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id The ID of the transaction.
     * @return The {@link Transaction} with the specified ID, or {@code null} if not found.
     */
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves a transaction by its taker's ID.
     *
     * @param id The ID of the taker.
     * @return The {@link Transaction} associated with the taker's ID, or {@code null} if not found.
     */
    public Transaction getTransactionByTakerId(Long id) {
        return transactionRepository.findByTakerId(id);
    }

    /**
     * Creates a new transaction.
     *
     * @param transaction The transaction to create.
     * @return The created {@link Transaction}.
     */
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    /**
     * Updates an existing transaction.
     *
     * @param id                The ID of the transaction to update.
     * @param updatedTransaction The updated transaction data.
     * @return The updated {@link Transaction}, or {@code null} if the transaction with the given ID is not found.
     */
    public Transaction updateTransaction(Long id, Transaction updatedTransaction) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction != null) {
            // Update the fields of existingTransaction with the fields of updatedTransaction

            return transactionRepository.save(existingTransaction);
        }
        return null;
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to delete.
     */
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
}
