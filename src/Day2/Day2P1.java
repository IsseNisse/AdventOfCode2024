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
                System.out.println(isSafe);
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
        if ((Integer.parseInt(levelsList[0]) - Integer.parseInt(levelsList[1])) < 0) {
            int prevRoof = Integer.parseInt(levelsList[0]) + ((levelsList.length - 1) * 3);
            int prevFloor = Integer.parseInt(levelsList[0]) + (levelsList.length - 1);

            for (int i = 0; i < levelsList.length; i++) {
                int currentLevel = Integer.parseInt(levelsList[i]);
                int roof = currentLevel + ((levelsList.length - i - 1) * 3);
                int floor = currentLevel + (levelsList.length - i - 1);

                if (roof > prevRoof || floor < prevFloor) {
                    return false;
                } else {
                    prevFloor = floor;
                    prevRoof = roof;
                }
            }
        } else {
            int prevFloor = Integer.parseInt(levelsList[0]) - ((levelsList.length - 1) * 3);
            int prevRoof = Integer.parseInt(levelsList[0]) - (levelsList.length - 1);

            for (int i = 0; i < levelsList.length; i++) {
                int currentLevel = Integer.parseInt(levelsList[i]);
                int floor = currentLevel - ((levelsList.length - i - 1) * 3);
                int roof = currentLevel - (levelsList.length - i - 1);

                if (floor < prevFloor || roof > prevRoof) {
                    return false;
                } else {
                    prevRoof = roof;
                    prevFloor = floor;
                }
            }
        }

        return true;
    }
}
