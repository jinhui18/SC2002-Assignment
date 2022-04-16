package Class;

/**
 * Represents an order's status.
 * @author Mary Soh
 * @version 1.1
 * @since 2022-04-12
 */
public enum StatusOrder {
    /**
     * The order is confirmed but no preparation of food has taken place.
     */
    CONFIRMED,
    /**
     * The items in the order have started to be prepared.
     */
    PREPARING,
    /**
     * The order and all its items has been delivered to the room.
     */
    DELIVERED;
}
