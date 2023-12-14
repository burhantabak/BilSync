package tr.edu.bilkent.bilsync.entity;

/**
 * Enum representing the possible states of a financial transaction.
 */
public enum TransactionState {
    PENDING_GIVER_APPROVAL,
    PENDING_TAKER_APPROVAL,
    DEPOSITED,
    REFUNDED,
}
