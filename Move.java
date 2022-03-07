/**
 * Represents a move made by a player
 *
 * @author prathaur3
 *
 * @version 1.0
 */
public class Move {

    private Ply whitePly;
    private Ply blackPly;

    /**
     * Creates a Move with all required parameters
     *
     * @param w the white player's move
     * @param b the black player's move
     *
     */
    public Move(Ply w, Ply b) {
        this.whitePly = w;
        this.blackPly = b;
    }

    /**
     * @return the white player's move
     */
    public Ply getWhitePly() {
        return whitePly;
    }

    /**
     * @return the black player's move
     */
    public Ply getBlackPly() {
        return blackPly;
    }
}