package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day3P1 {
    public static void main(String[] args) {
        ArrayList<String> extracted = new ArrayList<>();
        int total = 0;

        try {
            Scanner scanner = new Scanner(new File("./src/Day3/PuzzleInput.txt"));
            while(scanner.hasNext()) {
                String memory = scanner.nextLine();
                for (int i = 0; i < memory.length(); i++) {
                    int startIndex = memory.indexOf("mul(", i);
                    if (startIndex > 0) {
                        int endIndex = memory.indexOf(")", startIndex + 4) + 1;
                        if (endIndex < startIndex + 13) {
                            String mul = memory.substring(startIndex, endIndex);
                            extracted.add(mul);
                            i = startIndex + mul.length() - 1;
                        }
                    }
                }

                for (String current : extracted) {
                    current = current.substring(current.indexOf("(") + 1, current.indexOf(")"));
                    System.out.println(current);
                    if (current.indexOf(",") > 1 && current.indexOf(",") < 5) {
                        List<Integer> ints = Arrays.stream(current.split(",")).map(Integer::parseInt).toList();
                        total += ints.get(0) * ints.get(1);
                    }
                }

                System.out.println(total);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
