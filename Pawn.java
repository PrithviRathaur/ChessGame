/**
* Represents a Pawn that is a piece on a chess board.
*
* @author prathaur3
*
* @version 1.0
*
*/
public class Pawn extends Piece {

    private int[][] moveChange = {{1, 0}, {-1, 0}, {2, 0}, {-2, 0}};

    /**
    * Creates a Pawn with all required parameters
    *
    * @param color the color of the Pawn
    *
    */
    public Pawn(Color color) {
        super(color);
    }

    /**
    * @return the Pawn's algebraic name
    */
    @Override
    public String algebraicName() {
        return "";
    }

    /**
    * @return the Pawn's fen notation name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.WHITE) {
            return "P";
        } else {
            return "p";
        }
    }

    /**
    * @return the squares this Pawn could move to
    */
    @Override
    public Square[] movesFrom(Square square) {
        String squareString = square.toString();
        char file = squareString.charAt(0);
        char rank = squareString.charAt(1);
        int row;
        row = rank - 48;
        row = 8 - row;
        Square[] possibleMove = new Square[1];
        Square[] possibleMoveSpecial = new Square[2];
        if ((getColor() == Color.WHITE)) {
            if (row == 0) {
                Square[] promotion = new Square[0];
                return promotion;
            } else if (row == 6) {
                int rowChangeSpecial = moveChange[3][0];
                int rowChange = moveChange[1][0];
                int newRow = row + rowChange;
                int rowSpecial = row + rowChangeSpecial;
                newRow = 56 - newRow;
                rowSpecial = 56 - rowSpecial;
                rank = (char) newRow;
                char rankSpecial = (char) rowSpecial;
                possibleMoveSpecial[0] = new Square(file, rank);
                possibleMoveSpecial[1] = new Square(file, rankSpecial);
                return possibleMoveSpecial;
            } else {
                int rowChange = moveChange[1][0];
                row = row + rowChange;
                row = 56 - row;
                rank = (char) row;
                possibleMove[0] = new Square(file, rank);
                return possibleMove;
            }
        } else {
            if (row == 7) {
                Square[] promotion = new Square[0];
                return promotion;
            } else if (row == 1) {
                int rowChangeSpecial = moveChange[2][0];
                int rowChange = moveChange[0][0];
                int newRow = row + rowChange;
                int rowSpecial = row + rowChangeSpecial;
                newRow = 56 - newRow;
                rowSpecial = 56 - rowSpecial;
                rank = (char) newRow;
                char rankSpecial = (char) rowSpecial;
                possibleMoveSpecial[0] = new Square(file, rank);
                possibleMoveSpecial[1] = new Square(file, rankSpecial);
                return possibleMoveSpecial;
            } else {
                int rowChange = moveChange[0][0];
                row = row + rowChange;
                row = 56 - row;
                rank = (char) row;
                possibleMove[0] = new Square(file, rank);
                return possibleMove;
            }
        }
    }

}