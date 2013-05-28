package smog.missive;

import smog.exception.SmogException;

/**
 * DocumentInterface provides the interface for all missive document classes.
 *
 * @author Bishan Kumar Madhoo <bishan.madhoo@idsoft.mu>
 */
public interface DocumentInterface {

    // Build the missive document using its fragments
    public void build();

    // Validate a missive document
    public boolean validate() throws SmogException;
}
