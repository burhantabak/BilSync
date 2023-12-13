package tr.edu.bilkent.bilsync.service;

import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.TransactionDto;
import tr.edu.bilkent.bilsync.entity.Transaction;
import tr.edu.bilkent.bilsync.entity.TransactionState;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.NormalPostRepository;
import tr.edu.bilkent.bilsync.repository.TransactionRepository;

import java.util.Date;
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
     * @param trDto The transaction data object to turned into transaction.
     * @return The created {@link Transaction}.
     */
    public Transaction createTransaction(TransactionDto trDto,UserEntity currentUser) {
        Transaction transaction=new Transaction();
        transaction.setTransactionAmount(trDto.getTransactionAmount());
        transaction.setTakerId(currentUser.getId());
        transaction.setGiverId(trDto.getGiverId());
        transaction.setPostId(trDto.getPostId());
        transaction.setMoneyFetchDate(new Date()) ;
        transaction.setStatus(TransactionState.PENDING_GIVER_APPROVAL);
        return transactionRepository.save(transaction);
    }

    /**
     * Updates an existing transaction.
     *
     * @param id                The ID of the transaction to update.
     * @param updatedTransaction The updated transaction data.
     * @return The updated {@link Transaction}, or {@code null} if the transaction with the given ID is not found.
     */
    public Transaction updateTransaction(Long id, Transaction updatedTransaction, TransactionState newState) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction != null) {
           updatedTransaction.setStatus(newState);
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

    public List<Transaction> getTransactionsByPostId(Long postId) {
        return this.transactionRepository.findAllByPostId(postId);
    }
}
