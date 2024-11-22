package exceptions;

/**
 * Custom exception to indicate that an operation was attempted on an empty queue.
 */
public class EmptyQueueException extends RuntimeException {
    
    /**
     * Constructs an EmptyQueueException with no detail message.
     */
    public EmptyQueueException() {
        super();
    }

    /**
     * Constructs an EmptyQueueException with the specified detail message.
     * 
     * @param message the detail message
     */
    public EmptyQueueException(String message) {
        super(message);
    }
}
