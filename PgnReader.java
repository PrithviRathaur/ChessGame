import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PgnReader {

    // creates board
    private static char[][]
        board = {{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                 {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                 {'1', '1', '1', '1', '1', '1', '1', '1'},
                 {'1', '1', '1', '1', '1', '1', '1', '1'},
                 {'1', '1', '1', '1', '1', '1', '1', '1'},
                 {'1', '1', '1', '1', '1', '1', '1', '1'},
                 {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                 {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
            };

    /**
     * Find the tagName tag pair in a PGN game and return its value.
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm
     *
     * @param tagName the name of the tag whose value you want
     * @param game a `String` containing the PGN text of a chess game
     * @return the value in the named tag pair
     */
    public static String tagValue(String tagName, String game) {
        int firstIndexTag = game.indexOf(tagName);
        int firstIndexBracket = game.indexOf("]", firstIndexTag);
        if (firstIndexTag == -1) {
            return "NOT GIVEN";
        } else {
            return game.substring((firstIndexTag + tagName.length() + 2),
                (firstIndexBracket - 1));
        }
    }

    /**
     * Play out the moves in game and return a String with the game's
     * final position in Forsyth-Edwards Notation (FEN).
     *
     * @see http://www.saremba.de/chessgml/standards/pgn/pgn-complete.htm#c16.1
     *
     * @param game a `Strring` containing a PGN-formatted chess game or opening
     * @return the game's final position in FEN.
     */
    public static String finalPosition(String game) {
        int lastBracket = game.lastIndexOf("]");
        int startMove = game.indexOf("1", lastBracket);
        String moves = game.substring(startMove, game.length() - 1);
        String[] splitted = moves.split(" ");
        for (int i = 0; i < splitted.length; i++) {
            if ((i % 3 == 1) || (i % 3 == 2)) {
                String move = splitted[i];
                String numbers = "12345678";
                String oValue = "O";
                String equal = "=";
                int row = 0;
                int col = 0;
                for (int j = 0; j < move.length(); j++) {
                    char value = move.charAt(j);
                    if (numbers.contains(String.valueOf(value))) {
                        row = value - 48;
                        row = 8 - row;
                        col = (move.charAt(j - 1) - 97);
                        break;
                    }
                }
                boolean kingside = false;
                for (int j = 0; j < move.length(); j++) {
                    char value = move.charAt(j);
                    if (oValue.contains(String.valueOf(value))) {
                        int count = move.split("-").length;
                        kingside = (count == 2);
                        break;
                    }
                }
                char newPiece = 'p';
                for (int j = 0; j < move.length(); j++) {
                    char value = move.charAt(j);
                    if (equal.contains(String.valueOf(value))) {
                        int equalInd = move.indexOf("=");
                        int newPieceInd = equalInd + 1;
                        newPiece = move.charAt(newPieceInd);
                        // System.out.println(newPiece);
                        break;
                    }
                }
                // System.out.print(row);
                // System.out.print(col);
                boolean color = (i % 3) == 1;
                // System.out.print(color);
                boolean capture = move.contains("x");
                // System.out.println(capture);
                char piece = move.charAt(0);
                if (piece == 'K') {
                    kingMove(row, col, color);
                } else if (piece == 'Q') {
                    queenMove(row, col, color);
                } else if (piece == 'R') {
                    rookMove(row, col, color);
                } else if (piece == 'B') {
                    bishopMove(row, col, color);
                } else if (piece == 'N') {
                    knightMove(row, col, color);
                } else if (piece == 'O') {
                    castling(color, kingside);
                } else {
                    pawnMove(row, col, color, capture, newPiece);
                }
            }
        }
        String fenNotation = printBoard();
        return fenNotation;
    }

    public static void kingMove(int row, int col, boolean color) {
        char piece;
        if (color) {
            piece = 'K';
        } else {
            piece = 'k';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == piece) {
                    board[i][j] = '1';
                }
            }
        }
        board[row][col] = piece;
    }

    public static void queenMove(int row, int col, boolean color) {
        char piece;
        if (color) {
            piece = 'Q';
        } else {
            piece = 'q';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == piece) {
                    board[i][j] = '1';
                }
            }
        }
        board[row][col] = piece;
    }

    public static void rookMove(int row, int col, boolean color) {
        char piece;
        if (color) {
            piece = 'R';
        } else {
            piece = 'r';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == piece && (i == row || j == col)) {
                    board[i][j] = '1';
                }
            }
        }
        board[row][col] = piece;
    }

    public static void bishopMove(int row, int col, boolean color) {
        char piece;
        if (color) {
            piece = 'B';
        } else {
            piece = 'b';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == piece) {
                    if (row - i == col - j || i - row == col - j) {
                        board[i][j] = '1';
                        board[row][col] = piece;
                    }
                }
            }
        }
    }

    public static void knightMove(int row, int col, boolean color) {
        char piece;
        if (color) {
            piece = 'N';
        } else {
            piece = 'n';
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == piece) {
                    if ((row - i == -2 && col - j == 1)
                        || (row - i == -2 && col - j == -1)
                        || (row - i == -1 && col - j == -2)
                        || (row - i == -1 && col - j == 2)
                        || (row - i == 1 && col - j == -2)
                        || (row - i == 1 && col - j == 2)
                        || (row - i == 2 && col - j == -1)
                        || (row - i == 2 && col - j == 1)) {
                        board[i][j] = '1';
                        board[row][col] = piece;
                    }
                }
            }
        }
    }

    public static void castling(boolean color, boolean kingSide) {
        if (color) {
            if (kingSide) {
                int kingCol = 6;
                int rookCol = 5;
                board[7][4] = '1';
                board[7][7] = '1';
                board[7][kingCol] = 'K';
                board[7][rookCol] = 'R';
            } else {
                int kingCol = 2;
                int rookCol = 3;
                board[7][4] = '1';
                board[7][0] = '1';
                board[7][kingCol] = 'K';
                board[7][rookCol] = 'R';
            }
        } else {
            if (kingSide) {
                int kingCol = 6;
                int rookCol = 5;
                board[0][4] = '1';
                board[0][7] = '1';
                board[0][kingCol] = 'k';
                board[0][rookCol] = 'r';
            } else {
                int kingCol = 2;
                int rookCol = 3;
                board[0][4] = '1';
                board[0][0] = '1';
                board[0][kingCol] = 'k';
                board[0][rookCol] = 'r';
            }
        }
    }

    public static void pawnMove(int row, int col, boolean color,
        boolean capture, char newPiece) {
        char piece;
        if (capture) {
            if (color) {
                piece = 'P';
            } else {
                piece = 'p';
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == piece) {
                        if ((row - i == -1 && col - j == -1)
                            || (row - i == -1 && col - j == 1)
                            || (row - i == 1 && col - j == -1)
                            || (row - i == 1 && col - j == 1)) {
                            board[i][j] = '1';
                        }
                        if (row == 0) {
                            board[row][col] = newPiece;
                        } else {
                            board[row][col] = piece;
                        }
                    }
                }
            }
        } else {
            if (color) {
                piece = 'P';
            } else {
                piece = 'p';
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == piece) {
                        if ((row - i == -1 && col == j)
                            || (row - i == -2 && col == j)
                            || (row - i == 1 && col == j)
                            || (row - i == 2 && col == j)) {
                            board[i][j] = '1';
                        }
                        if (row == 7) {
                            board[row][col] = newPiece;
                        } else {
                            board[row][col] = piece;
                        }
                        //System.out.print("row: " + row);
                        //System.out.print(" col: " + col);
                        //System.out.print(" i: " + i);
                        //System.out.println(" j: " + j);
                    }
                }
            }
        }
    }

    public static String printBoard() {
        String fenString = "";
        int count;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != '1') {
                    if (board[i][j] == 'l') {
                        fenString = fenString;
                    } else {
                        fenString = fenString + board[i][j];
                    }
                } else {
                    count = 1;
                    for (int k = 1; k < 8 - j; k++) {
                        if (board[i][j + k] == '1') {
                            count = count + 1;
                            board[i][j + k] = 'l';
                        } else {
                            break;
                        }
                    }
                    fenString = fenString + count;
                }
                if (j == 7) {
                    fenString = fenString + "/";
                }
            }
        }
        return fenString;
    }

    /**
     * Reads the file named by path and returns its content as a String.
     *
     * @param path the relative or abolute path of the file to read
     * @return a String containing the content of the file
     */
    public static String fileContent(String path) {
        Path file = Paths.get(path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                // Add the \n that's removed by readline()
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
            System.exit(1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String game = fileContent(args[0]);
        System.out.format("Event: %s%n", tagValue("Event", game));
        System.out.format("Site: %s%n", tagValue("Site", game));
        System.out.format("Date: %s%n", tagValue("Date", game));
        System.out.format("Round: %s%n", tagValue("Round", game));
        System.out.format("White: %s%n", tagValue("White", game));
        System.out.format("Black: %s%n", tagValue("Black", game));
        System.out.format("Result: %s%n", tagValue("Result", game));
        System.out.println("Final Position:");
        System.out.println(finalPosition(game));
        /*for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                System.out.print(board[i][j]);
            }
            System.out.println(" ");
        */
    }
}
