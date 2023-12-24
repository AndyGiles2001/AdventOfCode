package main.year2020.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;
import main.utils.SetUtils;

public class Part2 {

    private static final int TARGET = 2020;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        int[] lines = reader.lines().mapToInt(Integer::parseInt).toArray();
        reader.close();

        for (int i = 0; i < lines.length - 2; i++) {
            for (int j = i + 1; j < lines.length - 1; j++) {
                for (int k = j + 1; k < lines.length; k++) {
                    if (lines[i] + lines[j] + lines[k] == TARGET) {
                        System.out.println(
                            String.format(
                                "%d * %d * %d = %d",
                                lines[i],
                                lines[j],
                                lines[k],
                                lines[i] * lines[j] * lines[k]
                            )
                        );
                        return;
                    }
                }
            }
        }

        System.out.println(String.format("No pair was found which adds to %d.", TARGET));
    }
}