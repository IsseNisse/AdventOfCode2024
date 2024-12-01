package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day1P2 {
    public static void main(String[] args) {
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();

        int similarityScore = 0;

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
                int occurrences = Collections.frequency(rightList, leftList.get(i));
                int score = leftList.get(i) * occurrences;
                similarityScore += score;
            }

            System.out.println(similarityScore);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
