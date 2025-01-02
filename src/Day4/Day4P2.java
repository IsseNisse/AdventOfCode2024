package Day4;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4P2 {
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
                if (row.get(j) == 'A') {
                    ArrayList<ArrayList<Integer>> MCoordinates = findNext('M', matrix, i, j);
                    if (MCoordinates.size() == 2) {
                        int both = 0;
                        for (ArrayList<Integer> MCoordinate : MCoordinates) {
                            int SXDiff = i - MCoordinate.get(0);
                            int SYDiff = j - MCoordinate.get(1);
                            if (i + SXDiff >= 0 && i + SXDiff < matrix.size()) {
                                if (j + SYDiff >= 0 && j + SYDiff < matrix.get(i).size()) {
                                    if (matrix.get(i + SXDiff).get(j + SYDiff) == 'S' && both == 1) {
                                        total++;
                                    } else if (matrix.get(i + SXDiff).get(j + SYDiff) == 'S') {
                                        both++;
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

            if (j < matrix.getFirst().size() - 1) {
                ArrayList<Integer> coordinate3 = new ArrayList<>();
                coordinate3.add(i - 1);
                coordinate3.add(j + 1);
                coordinates.add(coordinate3);
            }
        }
        if (i < matrix.size() - 1) {
            if (j >= 1) {
                ArrayList<Integer> coordinate6 = new ArrayList<>();
                coordinate6.add(i + 1);
                coordinate6.add(j - 1);
                coordinates.add(coordinate6);
            }

            if (j < matrix.getFirst().size() - 1) {
                ArrayList<Integer> coordinate8 = new ArrayList<>();
                coordinate8.add(i + 1);
                coordinate8.add(j + 1);
                coordinates.add(coordinate8);
            }
        }

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
