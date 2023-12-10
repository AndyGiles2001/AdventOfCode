import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.Math;

public class Part1 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] storedLines = bufferedReader
            .lines()
            .toArray(String[]::new);

        int maximumCalories = 0;
        int caloriesForThisElf = 0;
        for (String line : storedLines) {
            if (line.isEmpty()) {
                if (caloriesForThisElf > maximumCalories) {
                    maximumCalories = caloriesForThisElf;
                }

                caloriesForThisElf = 0;
            } else {
                caloriesForThisElf += Integer.parseInt(line);
            }
        }

        // in case the max elf was the last one
        int result = Math.max(maximumCalories, caloriesForThisElf);

        System.out.println(result);
    }
}
