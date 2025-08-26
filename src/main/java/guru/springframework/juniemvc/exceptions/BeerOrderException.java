package guru.springframework.juniemvc.exceptions;

/**
 * Exception thrown when there is an error related to apparel orders
 */
public class ApparelOrderException extends RuntimeException {
    
    public ApparelOrderException() {
        super();
    }
    
    public ApparelOrderException(String message) {
        super(message);
    }
    
    public ApparelOrderException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ApparelOrderException(Throwable cause) {
        super(cause);
    }
}