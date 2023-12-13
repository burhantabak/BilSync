package tr.edu.bilkent.bilsync.controller.controllerEntities;

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

    @Autowired
    private TransactionService transactionService;

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
     * Retrieves a transaction by its ID.
     *
     * @param id The ID of the transaction.
     * @return The {@link Transaction} with the specified ID, or {@code null} if not found.
     */
    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id);
    }

    /**
     * Creates a new transaction.
     *
     * @param transaction The transaction to create. Notice the giverId should be in the DTO!
     * @param currentUser The current user is always accepted as TAKER!
     * @return The created {@link Transaction}.
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto transaction, @AuthenticationPrincipal UserEntity currentUser) {
        if (transaction.getTransactionAmount() < 0) {
            // Return an error code as a String
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TRANSACTION_AMOUNT_NEGATIVE");
        }
        Transaction tr=transactionService.createTransaction(transaction, currentUser);
        return ResponseEntity.ok(tr);
    }


    /**
     * Updates the state of a transaction to "Giver Approved".
     *
     * @param id          The ID of the transaction to be updated.
     * @param transaction The updated transaction details.
     * @return The updated {@link Transaction} with the state set to {@link TransactionState#PENDING_TAKER_APPROVAL}.
     */
    @PutMapping("update/giverApproved/{id}")
    public Transaction updateToGiverApproved(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction, TransactionState.PENDING_TAKER_APPROVAL);
    }

    /**
     * Updates the state of a transaction to "Taker Approved".
     *
     * @param id          The ID of the transaction to be updated.
     * @param transaction The updated transaction details.
     * @return The updated {@link Transaction} with the state set to {@link TransactionState#DEPOSITED}.
     */
    @PutMapping("update/takerApproved/{id}")
    public Transaction updateToTakerApproved(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction, TransactionState.DEPOSITED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<Transaction>> getTransactionsByPostId(@PathVariable Long postId) {
        List<Transaction> transactions=transactionService.getTransactionsByPostId(postId);
        return ResponseEntity.ok(transactions);
    }


    //todo giving out dto or real obj?
    //todo refund durumuna end point yazmak mantıklı mı ama: çünkü zamana göre refund automated olmalı

    /**
     * Deletes a transaction by its ID.
     *
     * @param id The ID of the transaction to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
    }
}
