package tr.edu.bilkent.bilsync.entity;

/**
 * Enumeration representing the status of a user in a chat.
 * Possible values include:
 * - PENDING_REQUEST: Indicates that the user has a pending chat subscription request.
 * - SUBSCRIBED: Indicates that the user is subscribed to the chat.
 * - GROUP_ADMIN: Indicates that the user has administrative privileges in a group chat.
 */
public enum ChatUserStatus {
    PENDING_REQUEST,
    SUBSCRIBED,
    GROUP_ADMIN
}
