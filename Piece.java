/**
 * Represents the idea of a chess Piece.
 *
 * @author prathaur3
 *
 * @version 1.0
 *
 */
public abstract class Piece {

    private Color color;

    /**
     * Creates a Piece with all required parameters
     *
     * @param color the color of the piece being created
     *
     */
    public Piece(Color color) {
        this.color = color;
    }

    /**
    * @return the piece's color
    */
    public Color getColor() {
        return this.color;
    }

    /**
    * @return the piece's algebraic name
    */
    public abstract String algebraicName();

    /**
    * @return the piece's fen notation name
    */
    public abstract String fenName();

    /**
    *
    * @param square the initial starting square
    *
    * @return the squares this piece could move to
    */
    public abstract Square[] movesFrom(Square square);

}