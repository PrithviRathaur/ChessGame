import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * Represents a list of moves in a ChessGame
 *
 * @author prathaur3
 *
 * @version 1.0
 *
 */

public class ChessGame {

    private List<Move> moves;

    /**
     * Creates a ChessGame with all required parameters
     *
     * @param m the list of moves that occured in a ChessGame
     *
     */
    public ChessGame(List<Move> m) {
        this.moves = m;
    }

    /**
    * @return the moves in the ChessGame
    */
    public List<Move> getMoves() {
        return moves;
    }

    /**
    * @param n the integer move that we want
    *
    * @return the move specified by the integer
    */
    public Move getMove(int n) {
        return moves.get(n);
    }

    /**
    * @param f a predicate specifying how to filter list
    *
    * @return a filtered list of moves
    */
    public List<Move> filter(Predicate<Move> f) {
        List<Move> filteredM = new ArrayList();
        for (Move m : moves) {
            if (f.test(m)) {
                filteredM.add(m);
            }
        }
        return filteredM;
    }

    /**
    * @return a list of moves filtered by which have comments
    */
    public List<Move> getMovesWithComment() {
        List<Move> filteredM = filter(m ->
            m.getWhitePly().getComment().isPresent()
            || m.getBlackPly().getComment().isPresent());
        return filteredM;
    }

    /**
    * @return a list of moves filtered by those without comments
    */
    public List<Move> getMovesWithoutComment() {
        List<Move> filteredM = filter(new Predicate<Move>() {
            public boolean test(Move m) {
                return (!(m.getWhitePly().getComment().isPresent()
                    || m.getBlackPly().getComment().isPresent()));
            }
        });
        return filteredM;
    }

    /**
    * @param p get all the moves that occur for that piece
    *
    * @return the list of all the moves that occur to that piece
    *
    */
    public List<Move> getMovesWithPiece(Piece p) {
        class PiecePredicate implements Predicate<Move> {
            public boolean test(Move m) {
                return (p.fenName().equals(m.getWhitePly().getPiece().fenName())
                    ||
                    p.fenName().equals(m.getBlackPly().getPiece().fenName()));
            }
        }
        List<Move> filteredM = filter(new PiecePredicate());
        return filteredM;
    }
}