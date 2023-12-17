package tr.edu.bilkent.bilsync.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.bilkent.bilsync.dto.TransactionDto;
import tr.edu.bilkent.bilsync.entity.PostEntities.*;
import tr.edu.bilkent.bilsync.entity.Transaction;
import tr.edu.bilkent.bilsync.entity.TransactionState;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.repository.TransactionRepository;
import tr.edu.bilkent.bilsync.service.PostServices.PostService;
import tr.edu.bilkent.bilsync.service.PostServices.SecondHandTradingPostService;

import java.util.Date;
import java.util.List;

/**
 * Service class for managing transactions.
 */
@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserInfoService userService;
    private final PostService postService;

    /**
     * Constructs a new instance of the {@code TransactionService}.
     *
     * @param transactionRepository The repository for handling transactions.
     * @param userService           The user service to check the existence of users
     * @param postService           The repository for handling posts (assuming it exists).
     */
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserInfoService userService, PostService postService) {
        this.transactionRepository = transactionRepository;
        this.userService = userService;
        this.postService = postService;
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
     * @param postId The post that the transaction is based on.
     * @return The created {@link Transaction}.
     */
    public Transaction createTransaction(Long postId, UserEntity currentUser) {
        Post post = postService.getPostByID(postId);
        TradingPost tradingPost = null;
        double price = 0;
        UserEntity giver;
        if (post == null) {
            throw new EntityNotFoundException("Post does not exist");
        } else if (post instanceof SecondHandTradingPost) {
            SecondHandTradingPost secondHandTradingPost = (SecondHandTradingPost) post;
            price = secondHandTradingPost.getPrice();
            if (price < 0) {
                throw new IllegalStateException("The transaction amount cannot be negative");
            }
        } else if (post instanceof AnnouncementPost || post instanceof NormalPost) {
            throw new IllegalStateException("This item cannot be bought");
        }


        tradingPost = (TradingPost) post;


        if (tradingPost.getIsHeld()) {
            throw new IllegalStateException("The trading post is already in someone else's cart");
        } else if (tradingPost.isResolved()) {
            throw new IllegalStateException("The trading post is already bought by someone else");
        } else {
            giver = userService.findById(tradingPost.getAuthorID());
            if (giver == null) {
                throw new EntityNotFoundException("Giver does not exist");
            } else if (tradingPost.getAuthorID() == currentUser.getId()) {
                throw new IllegalStateException("It is not possible to trade the item that you are the lister of");
            }
            Transaction transaction = new Transaction();
            transaction.setTransactionAmount(price);
            transaction.setTakerId(currentUser.getId());
            transaction.setGiverId(post.getAuthorID());
            transaction.setPostId(postId);
            transaction.setMoneyFetchDate(new Date());
            transaction.setStatus(TransactionState.PENDING_GIVER_APPROVAL);
            postService.setHeld(postId, true);
            postService.setTakerId(tradingPost,currentUser.getId());
            return transactionRepository.save(transaction);
        }

    }

    /**
     * Updates an existing transaction.
     *
     * @param id The ID of the transaction to update.
     * @return The updated {@link Transaction}, or {@code null} if the transaction with the given ID is not found.
     */
    public Transaction updateTransaction(Long id, TransactionState newState) {
        Transaction existingTransaction = transactionRepository.findById(id).orElse(null);
        if (existingTransaction != null) {
            if (existingTransaction.getStatus() == TransactionState.REFUNDED || existingTransaction.getStatus() == TransactionState.DEPOSITED) {
                throw new IllegalStateException("Cannot update resolved transactions.");
            }
            existingTransaction.setStatus(newState);
            if (newState == TransactionState.PENDING_TAKER_APPROVAL) {
                existingTransaction.setGiverApproveDate(new Date());
            } else if (newState == TransactionState.DEPOSITED) {
                postService.setHeld(existingTransaction.getPostId(), false);
                postService.setAsResolved(existingTransaction.getPostId(), true);
                existingTransaction.setTakerApproveDate(new Date());
            }
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

    /**
     * Retrieves a list of transactions associated with a specific post ID.
     *
     * @param postId The unique identifier of the post for which transactions are to be retrieved.
     * @return A list of transactions associated with the specified post ID.
     * @throws IllegalArgumentException If the provided postId is null.
     */
    public List<Transaction> getTransactionsByPostId(Long postId) {
        return this.transactionRepository.findAllByPostId(postId);
    }


    public List<Transaction> getTransactionsByUser(UserEntity user) {
        // Retrieve transactions where the user is either taker or giver
        return transactionRepository.findByTakerIdOrGiverId(user.getId(), user.getId());
    }


    /**
     * Updates the state of transactions based on their associated dates.
     * Transactions with a specified threshold of days difference from the reference date
     * will have their state updated. The updated transactions are then saved to the repository.
     */
    public void updateTransactionStateBasedOnDates() {
        Date currentDate = new Date();

        for (Transaction transaction : this.transactionRepository.findAll()) {
            if (transaction.getMoneyFetchDate() != null) {
                if (transaction.getStatus() != TransactionState.REFUNDED ||
                        transaction.getStatus() != TransactionState.DEPOSITED) {

                    Date referenceDate = getReferenceDate(transaction);

                    if (referenceDate != null) {
                        // Calculate the difference in days
                        long daysDifference = calculateDaysDifference(currentDate, referenceDate);

                        // Assuming 3 days is the threshold for updating the state
                        if (daysDifference >= 3) {
                            // Update the transaction state here
                            transaction.setStatus(TransactionState.REFUNDED);
                            postService.setAsResolved(transaction.getPostId(), false);
                            postService.setHeld(transaction.getPostId(), false);
                            // Save the updated transaction to the repository
                            this.transactionRepository.save(transaction);
                        }
                    }
                }
            }
        }
    }

    /**
     * Retrieves the appropriate reference date based on the transaction status.
     *
     * @param transaction The transaction for which the reference date is needed.
     * @return The reference date based on the transaction status, or null if not applicable.
     */
    private Date getReferenceDate(Transaction transaction) {
        if (transaction.getStatus() == TransactionState.PENDING_GIVER_APPROVAL) {
            return transaction.getMoneyFetchDate();
        } else if (transaction.getStatus() == TransactionState.PENDING_TAKER_APPROVAL) {
            return transaction.getGiverApproveDate();
        } else {
            return null; // Handle other cases if needed
        }
    }

    /**
     * Calculates the difference in days between two dates.
     *
     * @param currentDate   The current date.
     * @param referenceDate The reference date.
     * @return The difference in days between the current date and the reference date.
     */
    private long calculateDaysDifference(Date currentDate, Date referenceDate) {
        long timeDifferenceMillis = currentDate.getTime() - referenceDate.getTime();
        return timeDifferenceMillis / (24 * 60 * 60 * 1000);
    }

}
