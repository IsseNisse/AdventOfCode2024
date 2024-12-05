package Day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4P1 {
    public static void main(String[] args) {
        int total = 0;
        ArrayList<ArrayList<Character>> matrix = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("./src/Day4/PuzzleInput.txt"));
            while(scanner.hasNext()) {
                char[] row = scanner.nextLine().toCharArray();
                ArrayList<Character> charList = new ArrayList<Character>();

                for (char c : row) {
                    charList.add(c);
                }

                matrix.add(charList);
            }

            findOccurrences(matrix);

            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static int findOccurrences(ArrayList<ArrayList<Character>> matrix) {
        int total = 0;
        for (int i = 0; i < matrix.size(); i++) {
            ArrayList<Character> row = matrix.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) == 'X') {
                    if (findNext('M', matrix, i, j)) {
                        if (findNext('A', matrix, i, j)) {
                            if (findNext('S', matrix, i, j)) {
                                total++;
                            }
                        }
                    }
                }
            }
        }
        return total;
    }

    private static boolean findNext(char toFind, ArrayList<ArrayList<Character>> matrix, int i, int j) {
        ArrayList<ArrayList<Integer>> coordinates = new ArrayList<>();
        if (i >= 1) {
            if (j >= 1) {
                ArrayList<Integer> coordinate = new ArrayList<>();
                coordinate.add(i - 1);
                coordinate.add(j - 1);
                coordinates.add(coordinate);
            }

            ArrayList<Integer> coordinate2 = new ArrayList<>();
            coordinate2.add(i - 1);
            coordinate2.add(j);
            coordinates.add(coordinate2);

            if (j < matrix.getFirst().size()) {
                ArrayList<Integer> coordinate3 = new ArrayList<>();
                coordinate3.add(i - 1);
                coordinate3.add(j + 1);
                coordinates.add(coordinate3);
            }
        }

        if (j >= 1) {
            ArrayList<Integer> coordinate4 = new ArrayList<>();
            coordinate4.add(i);
            coordinate4.add(j - 1);
            coordinates.add(coordinate4);
        }
        if (j < matrix.getFirst().size()) {
            ArrayList<Integer> coordinate5 = new ArrayList<>();
            coordinate5.add(i);
            coordinate5.add(j + 1);
            coordinates.add(coordinate5);
        }
        if (i < matrix.size()) {
            if (j >= 1) {
                ArrayList<Integer> coordinate6 = new ArrayList<>();
                coordinate6.add(i + 1);
                coordinate6.add(j - 1);
                coordinates.add(coordinate6);
            }
            ArrayList<Integer> coordinate7 = new ArrayList<>();
            coordinate7.add(i + 1);
            coordinate7.add(j);
            coordinates.add(coordinate7);

            if (j < matrix.getFirst().size()) {
                ArrayList<Integer> coordinate8 = new ArrayList<>();
                coordinate8.add(i + 1);
                coordinate8.add(j + 1);
                coordinates.add(coordinate8);
            }
        }
        System.out.println(coordinates);

        for (ArrayList<Integer> integers : coordinates) {
            char toCheck = matrix.get(integers.getFirst()).get(integers.get(1));
            if (toCheck == toFind) {
                return true;
            }
        }
        return false;
    }
}
