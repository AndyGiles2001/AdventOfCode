import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.Math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] storedLines = bufferedReader
            .lines()
            .toArray(String[]::new);

        List<Integer> elfCalories = new ArrayList<>();

        int caloriesForThisElf = 0;
        for (String line : storedLines) {
            if (line.isEmpty()) {
                elfCalories.add(caloriesForThisElf);
                caloriesForThisElf = 0;
            } else {
                caloriesForThisElf += Integer.parseInt(line);
            }
        }
        elfCalories.add(caloriesForThisElf);

        Collections.sort(elfCalories, Collections.reverseOrder());

        int result = elfCalories.get(0) + elfCalories.get(1) + elfCalories.get(2);

        System.out.println(result);
    }
}
