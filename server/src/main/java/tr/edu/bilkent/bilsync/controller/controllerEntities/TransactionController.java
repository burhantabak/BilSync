package tr.edu.bilkent.bilsync.controller.controllerEntities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.bilkent.bilsync.entity.Transaction;
import tr.edu.bilkent.bilsync.service.TransactionService;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to transactions.
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
     * @param transaction The transaction to create.
     * @return The created {@link Transaction}.
     */
    @PostMapping
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    /**
     * Updates an existing transaction by its ID.
     *
     * @param id          The ID of the transaction to update.
     * @param transaction The updated transaction data.
     * @return The updated {@link Transaction}, or {@code null} if the transaction with the given ID is not found.
     */
    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.updateTransaction(id, transaction);
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
}
