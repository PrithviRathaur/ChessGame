/**
* Represents a Knight that is a piece on a chess board.
*
* @author prathaur3
*
* @version 1.0
*
*/
public class Knight extends Piece {

    private static int[][] moveChange = {{-2, 1}, {-2, -1}, {-1, -2}, {-1, 2},
        {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    /**
    * Creates a Knight with all required parameters
    *
    * @param color the color of the Knight
    *
    */
    public Knight(Color color) {
        super(color);
    }

    /**
    * @return the Knight's algebraic name
    */
    @Override
    public String algebraicName() {
        return "N";
    }

    /**
    * @return the Knight's fen notation name
    */
    @Override
    public String fenName() {
        if (getColor() == Color.WHITE) {
            return "N";
        } else {
            return "n";
        }
    }

    /**
    * @return the squares this Knight could move to
    */
    @Override
    public Square[] movesFrom(Square square) {
        String squareString = square.toString();
        char file = squareString.charAt(0);
        char rank = squareString.charAt(1);
        int row;
        int col;
        col = file - 97;
        row = rank - 48;
        row = 8 - row;
        String movePosition = "";
        for (int i = 0; i < 8; i++) {
            int rankChange = moveChange[i][0];
            int fileChange = moveChange[i][1];
            int newCol = col + fileChange;
            int newRow = row + rankChange;
            if ((newRow > -1 & newCol > -1) & (newRow < 8 & newCol < 8)) {
                String strFile = String.valueOf(newCol);
                String strRank = String.valueOf(newRow);
                String rankAndFile = strRank + strFile;
                movePosition = movePosition + rankAndFile + " ";
            }
        }
        String[] positions = movePosition.split(" ");
        Square[] possibleMoves = new Square[positions.length];
        for (int i = 0; i < positions.length; i++) {
            String move = positions[i];
            rank = move.charAt(0);
            file = move.charAt(1);
            col = file + 49;
            row = rank - 48;
            row = 56 - row;
            file = (char) col;
            rank = (char) row;
            possibleMoves[i] = new Square(file, rank);
        }
        return possibleMoves;
    }

}