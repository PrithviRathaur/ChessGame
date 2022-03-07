import java.util.Optional;

/**
 * Represents a play in a chess game
 *
 * @author prathaur3
 *
 * @version 1.0
 *
 */

public class Ply {

    private Piece piece;
    private Square from;
    private Square to;
    private Optional<String> comment;

    /**
     * Creates a play with all required parameters
     *
     * @param p the piece on chessboard
     * @param f the original square
     * @param t the new square
     * @param c a comment for the move
     *
     */
    public Ply(Piece p, Square f, Square t, Optional<String> c) {
        this.piece = p;
        this.from = f;
        this.to = t;
        this.comment = c;
    }

    /**
     * @return the piece on chessboard
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @return the original square
     */
    public Square getFrom() {
        return from;
    }

    /**
     * @return the square it is going to
     */
    public Square getTo() {
        return to;
    }

    /**
     * @return the comment accompanying the play if there is one
     */
    public Optional<String> getComment() {
        return comment;
    }

}