package Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day2P1 {
    public static void main(String[] args) {
        int totalOfSafeReports = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/Day2/PuzzleInput.txt"));
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                String[] levelsList = currentLine.split(" ");
                boolean isSafe = isSafe(levelsList);
                if (isSafe) {
                    totalOfSafeReports++;
                }
            }
            System.out.println(totalOfSafeReports);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private static boolean isSafe(String[] levelsList) {
        int prevLevel = -1;
        int isDecreasing = 0;
        for (String s : levelsList) {
            int currentLevel = Integer.parseInt(s);
            if (prevLevel == -1) {
                prevLevel = currentLevel;
            } else {
                if (isDecreasing == 0) {
                    isDecreasing = prevLevel - currentLevel;
                }
                if (isDecreasing > 0) {
                    int diff = prevLevel - currentLevel;
                    if (diff <= 0 || diff > 3) {
                        return false;
                    }
                } else if (isDecreasing < 0) {
                    int diff = prevLevel - currentLevel;
                    if (diff >= 0 || diff < -3) {
                        return false;
                    }
                } else {
                    return false;
                }
            }

            prevLevel = currentLevel;
        }
        return true;
    }
}
