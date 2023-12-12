package tr.edu.bilkent.bilsync.entity;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents a financial transaction entity.
 */
@Entity
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The ID of the user who takes part in the transaction.
     */
    private Long takerId;

    /**
     * The ID of the user who initiates the transaction.
     */
    private Long giverId;

    /**
     * The ID of the post related to the transaction.
     */
    private Long postId;

    /**
     * The amount of money involved in the transaction.
     */
    private Double transactionAmount;

    /**
     * The date when the money is fetched for the transaction.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date moneyFetchDate;

    /**
     * The date when the seller approves the transaction.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date sellerApproveDate;

    /**
     * The date when the buyer approves the transaction.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date buyerApproveDate;

    /**
     * The current state of the transaction.
     */
    private TransactionState status;

    /**
     * Sets the unique identifier for the transaction.
     *
     * @param id The transaction ID to set.
     */
    public void setId(Long id) {
        this.id = id;
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
     * Sets the ID of the post related to the transaction.
     *
     * @param postId The post ID to set.
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * Sets the amount of money involved in the transaction.
     *
     * @param transactionAmount The transaction amount to set.
     */
    public void setTransactionAmount(Double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Sets the date when the money is fetched for the transaction.
     *
     * @param moneyFetchDate The money fetch date to set.
     */
    public void setMoneyFetchDate(Date moneyFetchDate) {
        this.moneyFetchDate = moneyFetchDate;
    }

    /**
     * Sets the date when the seller approves the transaction.
     *
     * @param sellerApproveDate The seller approval date to set.
     */
    public void setSellerApproveDate(Date sellerApproveDate) {
        this.sellerApproveDate = sellerApproveDate;
    }

    /**
     * Sets the date when the buyer approves the transaction.
     *
     * @param buyerApproveDate The buyer approval date to set.
     */
    public void setBuyerApproveDate(Date buyerApproveDate) {
        this.buyerApproveDate = buyerApproveDate;
    }

    /**
     * Sets the current state of the transaction.
     *
     * @param status The transaction status to set.
     */
    public void setStatus(TransactionState status) {
        this.status = status;
    }

    /**
     * Retrieves the unique identifier for the transaction.
     *
     * @return The transaction ID.
     */
    public Long getId() {
        return id;
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
     * Retrieves the ID of the post related to the transaction.
     *
     * @return The post ID.
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * Retrieves the amount of money involved in the transaction.
     *
     * @return The transaction amount.
     */
    public Double getTransactionAmount() {
        return transactionAmount;
    }

    /**
     * Retrieves the date when the money is fetched for the transaction.
     *
     * @return The money fetch date.
     */
    public Date getMoneyFetchDate() {
        return moneyFetchDate;
    }

    /**
     * Retrieves the date when the seller approves the transaction.
     *
     * @return The seller approval date.
     */
    public Date getSellerApproveDate() {
        return sellerApproveDate;
    }

    /**
     * Retrieves the date when the buyer approves the transaction.
     *
     * @return The buyer approval date.
     */
    public Date getBuyerApproveDate() {
        return buyerApproveDate;
    }

    /**
     * Retrieves the current state of the transaction.
     *
     * @return The transaction status.
     */
    public TransactionState getStatus() {
        return status;
    }
}
