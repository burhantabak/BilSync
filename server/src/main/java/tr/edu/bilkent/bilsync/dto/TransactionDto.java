package tr.edu.bilkent.bilsync.dto;

import tr.edu.bilkent.bilsync.entity.Transaction;
import tr.edu.bilkent.bilsync.entity.TransactionState;

/**
 * Data Transfer Object (DTO) representing a simplified view of a {@link Transaction}.
 */
public class TransactionDto {

    /**
     * The current state of the transaction.
     */
    private TransactionState status;

    /**
     * ID of the taker in the transaction.
     */
    private Long takerId;

    /**
     * ID of the giver in the transaction.
     */
    private Long giverId;

    /**
     * ID of the post the transaction is based on.
     */
    private Long postId;

    /**
     * Sets the ID of the post in the transaction.
     *
     * @param postId The post's ID to set.
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }


    /**
     * Retrieves the ID of the post in the transaction.
     *
     * @return The post's ID.
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * Amount of the transaction.
     */
    private Double transactionAmount;

    /**
     * Constructs a {@code TransactionDto} based on the given {@link Transaction}.
     *
     * @param tr The source {@link Transaction} to create the DTO from.
     */
    public TransactionDto(Transaction tr) {
        this.takerId = tr != null ? tr.getTakerId() : null;
        this.giverId = tr != null ? tr.getGiverId() : null;
        this.transactionAmount = tr != null ? tr.getTransactionAmount() : null;
    }

    /**
     * Retrieves the ID of the taker in the transaction.
     *
     * @return The taker's ID.
     */
    public Long getTakerId() {
        return takerId;
    }

    /**
     * Retrieves the ID of the giver in the transaction.
     *
     * @return The giver's ID.
     */
    public Long getGiverId() {
        return giverId;
    }

    /**
     * Retrieves the amount of the transaction.
     *
     * @return The transaction amount.
     */
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Sets the ID of the taker in the transaction.
     *
     * @param takerId The taker's ID to set.
     */
    public void setTakerId(Long takerId) {
        this.takerId = takerId;
    }

    /**
     * Sets the ID of the giver in the transaction.
     *
     * @param giverId The giver's ID to set.
     */
    public void setGiverId(Long giverId) {
        this.giverId = giverId;
    }


    /**
     * Retrieves the current status of the transaction.
     *
     * @return The current {@link TransactionState} of the transaction.
     */
    public TransactionState getStatus() {
        return status;
    }

    /**
     * Sets the status of the transaction to the specified {@link TransactionState}.
     *
     * @param status The new {@link TransactionState} to set for the transaction.
     */
    public void setStatus(TransactionState status) {
        this.status = status;
    }


    /**
     * Sets the amount of the transaction.
     *
     * @param transactionAmount The transaction amount to set.
     */
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Constructs a {@code TransactionDto} with the specified parameters.
     *
     * @param takerId           The taker's ID.
     * @param giverId           The giver's ID.
     * @param transactionAmount The transaction amount.
     */
    public TransactionDto(Long takerId, Long giverId, Double transactionAmount) {
        this.takerId = takerId;
        this.giverId = giverId;
        this.transactionAmount = transactionAmount;
    }
}
