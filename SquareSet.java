import java.util.Set;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Represents the a Set of Square objects
 *
 * @author prathaur3
 *
 * @version 1.0
 *
 */

public class SquareSet implements Set<Square> {

    private Square[] set;
    private int lastIndex = 0;
    private String validFiles = "abcdefgh";
    private String validRanks = "12345678";

    /**
     * Creates a SquareSet that has no arguments passed
     */
    public SquareSet() {
        this.set = new Square[64];
    }

    /**
     * Creates a SquareSet with all required parameters
     *
     * @param c the collection that contains squares that we want to make
     * a set from.
     *
     */
    public SquareSet(Collection<Square> c) {
        Iterator cItr = c.iterator();
        this.set = new Square[64];
        while (cItr.hasNext()) {
            Square curSquare = (Square) cItr.next();
            this.add(curSquare);
        }
    }

    @Override
    public boolean add(Square square) {
        if (square == null) {
            throw new NullPointerException();
        }
        char rank = square.getRank();
        char file = square.getFile();
        if ((!(validFiles.indexOf(file) >= 0))) {
            String message = "" + file + rank;
            throw new InvalidSquareException(message);
        } else if ((!(validRanks.indexOf(rank) >= 0))) {
            String message = "" + file + rank;
            throw new InvalidSquareException(message);
        }
        boolean contains = this.contains(square);
        if (!contains) {
            if (lastIndex + 1 == this.size()) {
                set[lastIndex] = square;
                Square[] tempArr = new Square[(lastIndex + 1) * 2];
                for (int i = 0; i < (lastIndex + 1); i++) {
                    tempArr[i] = set[i];
                }
                set = tempArr;
                lastIndex++;
            } else {
                set[lastIndex] = square;
                lastIndex++;
            }
        }
        return (!contains);
    }

    @Override
    public boolean addAll(Collection<? extends Square> c) {
        Iterator cItr = c.iterator();
        int count = 0;
        while (cItr.hasNext()) {
            count = count + 1;
            Square curSquare = (Square) cItr.next();
        }
        Iterator rItr = c.iterator();
        Square[] tempArr = new Square[count];
        for (int i = 0; i < tempArr.length; i++) {
            Square curSquare = (Square) rItr.next();
            tempArr[i] = curSquare;
        }
        boolean changed = false;
        for (int i = 0; i < tempArr.length; i++) {
            char rank = tempArr[i].getRank();
            char file = tempArr[i].getFile();
            System.out.print(rank);
            System.out.println(file);
            System.out.println(validFiles.indexOf(file));
            System.out.println(validRanks.indexOf(rank));
            if ((!(validFiles.indexOf(file) >= 0))) {
                throw new InvalidSquareException();
            } else if ((!(validRanks.indexOf(rank) >= 0))) {
                throw new InvalidSquareException();
            } else {
                for (int j = 0; j < tempArr.length; j++) {
                    if (!(this.add(tempArr[i]))) {
                        continue;
                    } else {
                        changed = true;
                    }
                }

            }
        }
        return changed;
    }

    @Override
    public boolean contains(Object o) {
        boolean contains = false;
        if (o == null) {
            throw new NullPointerException();
        } else {
            for (int i = 0; i < lastIndex; i++) {
                if (set[i].equals(o)) {
                    contains = true;
                } else {
                    continue;
                }
            }
        }
        return contains;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator cItr = c.iterator();
        while (cItr.hasNext()) {
            Square curSquare = (Square) cItr.next();
            boolean contains = true;
            if (!(this.contains(curSquare))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other == this) {
            return true;
        } else if (!(other instanceof Set)) {
            return false;
        } else {
            Iterator otherItr = ((Set) other).iterator();
            while (otherItr.hasNext()) {
                Square curSquare = (Square) otherItr.next();
                if (this.contains(curSquare)) {
                    continue;
                } else {
                    return false;
                }
            }
            return this.size() == (((Set) other).size());
        }
    }

    @Override
    public int hashCode() {
        int sum = 0;
        for (int i = 0; i < set.length - 1; i++) {
            Square curObject = set[i];
            if (curObject == null) {
                continue;
            } else {
                sum = sum + curObject.hashCode();
            }
        }
        return sum;
    }

    @Override
    public boolean isEmpty() {
        boolean empty = true;
        for (int i = 0; i < set.length - 1; i++) {
            Square curObject = set[i];
            if (curObject == null) {
                continue;
            } else {
                return false;
            }
        }
        return empty;
    }

    @Override
    public int size() {
        int size = lastIndex;
        return size;
    }

    @Override
    public Object[] toArray() {
        Square[] array = new Square[lastIndex];
        for (int i = 0; i < lastIndex; i++) {
            array[i] = set[i];
        }
        return array;
    }

    @Override
    public Square[] toArray(Object[] a) {
        // Class type = a.getClass();
        // if (type.isAssignableFrom(Square.class)) {
        //     throw new ArrayStoreException();
        // }
        // if (a.length == this.size()) {
        //     a = (Object[]) set;
        //     return a;
        // } else if (a.length > this.size()) {
        //     for (int i = 0; i < this.size(); i++) {
        //         a[i] = (Object) set[i];
        //         System.out.println(set[i]);
        //     }
        //     a[this.size()] = null;
        //     return a;
        // } else {
        //     Square[] array = new Square[this.size()];
        //     for (int i = 0; i < lastIndex; i++) {
        //         array[i] = set[i];
        //     }
        //     return (Object[]) array;
        // }
        return new Square[4];

    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            throw new NullPointerException();
        } else if (!(o instanceof Square)) {
            throw new ClassCastException();
        } else {
            if (this.contains(o)) {
                Square[] tempArr = new Square[set.length];
                int count = 0;
                for (int i = 0; i < lastIndex; i++) {
                    if (set[i].equals(o)) {
                        set[i] = null;
                    } else {
                        tempArr[count] = set[i];
                        count++;
                    }
                }
                set = tempArr;
                lastIndex = lastIndex - 1;
                return true;
            } else {
                return false;
            }

        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    private class SquareItr implements Iterator<Square> {

        private int cursor = 0;

        public boolean hasNext() {
            return cursor < lastIndex;
        }

        public Square next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                Square nextSquare = set[cursor];
                cursor++;
                return nextSquare;
            }
        }
    }

    @Override
    public Iterator<Square> iterator() {
        return new SquareItr();
    }
}