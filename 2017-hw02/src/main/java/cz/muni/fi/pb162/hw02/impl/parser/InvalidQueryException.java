package cz.muni.fi.pb162.hw02.impl.parser;


/**
 * @author Jakub Polacek
 */


public class InvalidQueryException extends Exception {

    /**
     * Constructor with default message.
     */
    public InvalidQueryException() {
        super("Incorret query input.");
    }

    /**
     * Constructor with custom description
     *
     * @param description provided description of exception
     */
    public InvalidQueryException(String description) {
        super(description);
    }

}
