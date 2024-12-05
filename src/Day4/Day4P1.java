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

            System.out.println(findOccurrences(matrix));

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
                    for (ArrayList<Integer> MCoordinate : findNext('M', matrix, i, j)) {
                        int x = i - MCoordinate.get(0);
                        int y = j - MCoordinate.get(1);

                        for (ArrayList<Integer> ACoordinate : findNext('A', matrix, MCoordinate.get(0), MCoordinate.get(1))) {
                            int x2 = MCoordinate.get(0) - ACoordinate.get(0);
                            int y2 = MCoordinate.get(1) - ACoordinate.get(1);
                            if (x == x2 && y == y2) {
                                for(ArrayList<Integer> SCoordinate : findNext('S', matrix, ACoordinate.get(0), ACoordinate.get(1))) {
                                    int x3 = ACoordinate.get(0) - SCoordinate.get(0);
                                    int y3 = ACoordinate.get(1) - SCoordinate.get(1);
                                    if (x2 == x3 && y2 == y3) {
                                        total++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return total;
    }

    private static ArrayList<ArrayList<Integer>> findNext(char toFind, ArrayList<ArrayList<Character>> matrix, int i, int j) {
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

            if (j < matrix.getFirst().size() - 1) {
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
        if (j < matrix.getFirst().size() - 1) {
            ArrayList<Integer> coordinate5 = new ArrayList<>();
            coordinate5.add(i);
            coordinate5.add(j + 1);
            coordinates.add(coordinate5);
        }
        if (i < matrix.size() - 1) {
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

            if (j < matrix.getFirst().size() - 1) {
                ArrayList<Integer> coordinate8 = new ArrayList<>();
                coordinate8.add(i + 1);
                coordinate8.add(j + 1);
                coordinates.add(coordinate8);
            }
        }
        //System.out.println(coordinates);
        ArrayList<ArrayList<Integer>> nextCoordinates = new ArrayList<>();
        for (ArrayList<Integer> integers : coordinates) {
            char toCheck = matrix.get(integers.getFirst()).get(integers.get(1));
            if (toCheck == toFind) {
                nextCoordinates.add(integers);
            }
        }
        return nextCoordinates;
    }
}
