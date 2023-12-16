package tr.edu.bilkent.bilsync.controller.controllerEntities;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.dto.TransactionDto;
import tr.edu.bilkent.bilsync.entity.Transaction;
import tr.edu.bilkent.bilsync.entity.TransactionState;
import tr.edu.bilkent.bilsync.entity.UserEntity;
import tr.edu.bilkent.bilsync.service.TransactionService;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Controller class for handling HTTP requests related to transactions.
 * The expected flow of transactions:
 * 1-Taker clicks to buy:  transaction in PENDING_GIVER_APPROVAL state (for delivery)
 * 2-Giver approves that they delivered the item, transaction in  PENDING_TAKER_APPROVAL (for getting the delivery safely)
 * 3.1- if Taker approves that they took the item transaction in DEPOSITED state (successful ending)
 * 3.2- else transaction in REFUNDED state (unsuccessful ending)
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    /**
     * Retrieves a list of all transactions.
     *
     * @return A list of all transactions.
     */
    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    /**
     * Retrieves a transaction by its unique identifier.
     *
     * @param id The identifier of the transaction to retrieve.
     * @return A ResponseEntity containing the transaction if found, or a BAD_REQUEST response
     * with an error message if the transaction does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        Transaction tr = transactionService.getTransactionById(id);
        if (tr == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such transaction");
        } else {
            return ResponseEntity.ok(tr);
        }
    }


    /**
     * Retrieves a list of transactions for the authenticated user, either as the taker or giver.
     *
     * @param currentUser The authenticated user for whom transactions are to be retrieved.
     * @return A ResponseEntity containing the list of transactions if found, or a BAD_REQUEST response
     * with an error message if no transactions are associated with the user.
     */
    @GetMapping("/by-user")
    public ResponseEntity<?> getTransactionsByUser(@AuthenticationPrincipal UserEntity currentUser) {
        List<Transaction> userTransactions = transactionService.getTransactionsByUser(currentUser);
        if(userTransactions.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no transaction");
        }
        return ResponseEntity.ok(userTransactions);
    }

    /**
     * Creates a new transaction.
     *
     * @param postId post that the transaction is based from
     * @param currentUser The current user is always accepted as TAKER!
     * @return The created {@link Transaction}.
     */
    @PostMapping("/create/{postId}")
    public ResponseEntity<?> createTransaction(@PathVariable Long postId, @AuthenticationPrincipal UserEntity currentUser) {
        try {
            Transaction tr = transactionService.createTransaction(postId, currentUser);
            return ResponseEntity.ok(tr);
        } catch (EntityNotFoundException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Updates the state of a transaction to "Giver Approved".
     *
     * @param id The ID of the transaction to be updated.
     * @return The updated {@link Transaction} with the state set to {@link TransactionState#PENDING_TAKER_APPROVAL}.
     */
    @PutMapping("update/giverApproved/{id}")
    public ResponseEntity<?> updateToGiverApproved(@PathVariable Long id) {
        return handleTransactionUpdate(id, () -> transactionService.updateTransaction(id, TransactionState.PENDING_TAKER_APPROVAL));
    }

    /**
     * Updates the state of a transaction to "Taker Approved".
     *
     * @param id The ID of the transaction to be updated.
     * @return The updated {@link Transaction} with the state set to {@link TransactionState#DEPOSITED}.
     */
    @PutMapping("update/takerApproved/{id}")
    public ResponseEntity<?> updateToTakerApproved(@PathVariable Long id) {
        return handleTransactionUpdate(id, () -> transactionService.updateTransaction(id, TransactionState.DEPOSITED));
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<Transaction>> getTransactionsByPostId(@PathVariable Long postId) {
        List<Transaction> transactions = transactionService.getTransactionsByPostId(postId);
        return ResponseEntity.ok(transactions);
    }

    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }

    //helper method
    private ResponseEntity<?> handleTransactionUpdate(Long id, Supplier<Transaction> updateFunction) {
        try {
            Transaction tr = updateFunction.get();
            if (tr == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("There is no such transaction");
            } else {
                // Handle success case if needed
                return ResponseEntity.ok("Transaction updated successfully");
            }
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
