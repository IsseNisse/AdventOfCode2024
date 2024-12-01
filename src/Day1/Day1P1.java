package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1P1 {
    public static void main(String[] args) {
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        int totalDistance = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/Day1/PuzzleInput.txt"));
            while (scanner.hasNextLine()) {
                String currentLine = scanner.nextLine();
                currentLine = currentLine.trim().replaceAll(" +", " ");
                leftList.add(Integer.parseInt(currentLine.split(" ")[0]));
                rightList.add(Integer.parseInt(currentLine.split(" ")[1]));
            }

            Collections.sort(leftList);
            Collections.sort(rightList);

            for (int i = 0; i < leftList.size(); i++) {
                int diff = Math.abs(leftList.get(i) - rightList.get(i));
                totalDistance += diff;
            }

            System.out.println(totalDistance);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
