package Day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3P2 {
    public static void main(String[] args) {
        int total = 0;
        boolean disabled = false;

        try {
            Scanner scanner = new Scanner(new File("./src/Day3/PuzzleInput.txt"));
            while(scanner.hasNext()) {
                String memory = scanner.nextLine();
                ArrayList<String> extracted = new ArrayList<>();


                Matcher m = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))|(do\\(\\)|don't\\(\\))").matcher(memory);
                while (m.find()) {
                    if (Objects.equals(m.group(), "don't()")) {
                        disabled = true;
                    } else if (Objects.equals(m.group(), "do()")) {
                        disabled = false;
                    } else {
                        if (!disabled) {
                            System.out.println(m.group());
                            extracted.add(m.group());
                        }
                    }
                }

                for (int i = 0; i < extracted.size(); i++) {
                    m = Pattern.compile("\\d+").matcher(extracted.get(i));
                    ArrayList<Integer> ints = new ArrayList<>();
                    while (m.find()) {
                        ints.add(Integer.parseInt(m.group()));
                    }

                    total += ints.get(0) * ints.get(1);
                }
            }
            System.out.println(total);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
