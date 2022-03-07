import java.util.ArrayList;
import java.util.Arrays;

public class myExceptionTester {

    private static void myTester(String[] names) {

        for (String square: names) {
            try {
                new Square(square);
                System.out.println("PASS " + square);
            } catch (InvalidSquareException e) {
                System.out.println("ERROR " + e.getMessage());
            }
        } }
    private static String[] test1 = new String[] {"a2", "d5", "f9", "g12", "t2", "c22", "e4"};


    public static void main(String[] args) throws InvalidSquareException {
        myTester(test1);
//for character constructor
        try {
            new Square('a','3');
            System.out.println("PASS a3");
        } catch (InvalidSquareException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        try {
            new Square('i','8');
            System.out.println("PASS i8");
        } catch (InvalidSquareException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        try {
            new Square('b','9');
            System.out.println("PASS b9");
        } catch (InvalidSquareException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        Square s = new Square("f5");
        System.out.println("file:" + s.getFile());
        System.out.println("rank:" + s.getRank());
        System.out.println("full name:" + s.toString());

    }
}