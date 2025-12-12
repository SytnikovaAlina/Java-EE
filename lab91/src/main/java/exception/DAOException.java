package exception;

public class DAOException extends Exception {
    // Constructor with message
    public DAOException(String message) {
        super(message);
    }
    
    // Constructor with cause (Throwable)
    public DAOException(Throwable cause) {
        super(cause);
    }
    
    // Constructor with message and cause
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
