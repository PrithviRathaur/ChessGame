/**
 * Represents the location of a piece
 *
 * @author prathaur3
 *
 * @version 1.0
 *
 */


public class Square {
    private char file;
    private char rank;
    private String validFiles = "abcdefgh";
    private String validRanks = "12345678";

    /**
     * Creates a square with all required parameters
     *
     * @param file the column that the square is found on in chess notation
     * @param rank the row that the square is found on in chess notation
     *
     */
    public Square(char file, char rank) throws InvalidSquareException {
        if ((!(validFiles.indexOf(file) >= 0))) {
            String message = "" + file + rank;
            throw new InvalidSquareException(message);
        } else if ((!(validRanks.indexOf(rank) >= 0))) {
            String message = "" + file + rank;
            throw new InvalidSquareException(message);
        }
        this.file = file;
        this.rank = rank;
    }

    /**
     * Creates a square with all required parameters
     *
     * @param name the square's chess notation name where file and rank
     * together
     */
    public Square(String name) throws InvalidSquareException {
        char tempFile = name.charAt(0);
        char tempRank = name.charAt(1);
        if (name.length() > 2) {
            throw new InvalidSquareException(name);
        }
        if ((!(validFiles.indexOf(tempFile) >= 0))) {
            throw new InvalidSquareException(name);
        } else if ((!(validRanks.indexOf(tempRank) >= 0))) {
            throw new InvalidSquareException(name);
        }
        this.file = name.charAt(0);
        this.rank = name.charAt(1);
    }

    /**
     * @return this square's chess notation location
     */
    public String toString() { // toString method
        String strFile = String.valueOf(this.file);
        String strRank = String.valueOf(this.rank);
        return strFile + strRank;
    }

    /**
     * @return the file of square
     */
    public char getFile() {
        return file;
    }

    /**
     * @return the rank of square
     */
    public char getRank() {
        return rank;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Square)) {
            return false;
        }
        Square that = (Square) other;
        return this.file == that.file && this.rank == that.rank;
    }

}