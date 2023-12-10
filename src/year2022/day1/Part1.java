package year2022.day1;

import java.io.BufferedReader;
import java.io.IOException;

import java.lang.Math;

import utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        String[] storedLines = bufferedReader
            .lines()
            .toArray(String[]::new);

        bufferedReader.close();

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
