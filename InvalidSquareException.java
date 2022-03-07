/**
 * Checks for whether or not the square created is a valid square on a
 * chess board.
 *
 * I believe this exception should be an checked exception because the reason
 * this error would occur is because the user inputted a square value that can't
 * exist.
 *
 * @author prathaur3
 *
 * @version 1.0
 *
 */
public class InvalidSquareException extends RuntimeException {

    /**
     * Creates an exception without any parameters
     *
     */
    public InvalidSquareException() {
    }

    /**
     * Creates an exception with all required parameters
     *
     * @param msg error message
     *
     */
    public InvalidSquareException(String msg) {
        super(msg);
    }
}