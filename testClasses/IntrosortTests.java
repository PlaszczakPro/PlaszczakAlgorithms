package testClasses;

import Other.Introsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class IntrosortTests {
    public static void main(String[] args) throws IOException {
        test1();

    }

    private static void test1() throws IOException {
        try {
            String file = "res/IntrosortTestFiles/testInput1.txt";

            List<Integer> arrList = new ArrayList<>();
            try (Scanner fileInput = new Scanner(new File(file))) {
                fileInput.useDelimiter("[,\\s]+");
                while (fileInput.hasNextInt()) {
                    arrList.add(fileInput.nextInt());
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            Introsort<Integer> introsort = new Introsort<>();
            introsort.sortList(arrList);

            try (PrintWriter output = new PrintWriter(file + ".out")) {
                for (int i : arrList) {
                    output.print(i + " ");
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
