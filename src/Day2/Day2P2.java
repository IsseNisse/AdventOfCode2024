package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Day2P2 {
    public static void main(String[] args) {
        int totalOfSafeReports = 0;
        int totalTolerance = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/Day2/PuzzleInput.txt"));
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] levelsList = currentLine.split(" ");
                ArrayList<Integer> levels = new ArrayList<>();
                for (int i = 0; i < levelsList.length; i++) {
                    levels.add(Integer.parseInt(levelsList[i]));
                }
                boolean isSafe = isSafe(levels);
                boolean tolerance = withTolerance(levels);
                if (isSafe) {
                    totalOfSafeReports++;
                } else if (tolerance) {
                    totalTolerance++;
                }
            }
            System.out.println(totalOfSafeReports);
            System.out.println(totalTolerance);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static boolean isSafe(ArrayList<Integer> levels) {
        for (int i = 1; i < levels.size(); i++) {
            if (Math.abs(levels.get(i - 1) - levels.get(i)) < 1 || Math.abs(levels.get(i - 1) - levels.get(i)) > 3) {
                return false;
            }
        }
        ArrayList<Integer> ascList = new ArrayList<>(levels);
        ArrayList<Integer> descList = new ArrayList<>(levels);
        Collections.sort(ascList);
        descList.sort(Collections.reverseOrder());
        if (ascList.equals(levels)) {
            return true;
        } else return descList.equals(levels);
    }

    private static boolean withTolerance(ArrayList<Integer> levels) {
        boolean isSafe = false;
        for (int i = 0; i < levels.size(); i++) {
            ArrayList<Integer> skippedList = new ArrayList<>(levels);
            skippedList.remove(i);
            if (isSafe(skippedList)) {
                return true;
            }
        }
        return isSafe;
    }
}
