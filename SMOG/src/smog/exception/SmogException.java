package smog.exception;

/**
 * SmogException is the exception raised when an error occurs in the SMOG library.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public class SmogException extends Exception {

    /**
     * SmogException constructor
     *
     * @param message Error message
     */
    public SmogException(String message) {

        // Initialise the parent class
        super(message);
    }
}
