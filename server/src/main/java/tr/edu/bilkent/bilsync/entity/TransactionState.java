package tr.edu.bilkent.bilsync.entity;

/**
 * Enumeration representing the states of a transaction.
 * Possible values include:
 * - PENDING_GIVER_APPROVAL: Indicates that the transaction is pending approval from the giver.
 * - PENDING_TAKER_APPROVAL: Indicates that the transaction is pending approval from the taker.
 * - DEPOSITED: Indicates that the transaction has been deposited.
 * - REFUNDED: Indicates that the transaction has been refunded.
 */
public enum TransactionState {
    PENDING_GIVER_APPROVAL,
    PENDING_TAKER_APPROVAL,
    DEPOSITED,
    REFUNDED,
}
