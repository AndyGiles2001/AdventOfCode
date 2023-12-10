package year2023.day6;

import java.io.BufferedReader;
import java.io.IOException;

import java.lang.Math;

import utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        String[] storedLines = bufferedReader
            .lines()
            .toArray(String[]::new);

        bufferedReader.close();

        long time = extractLong(storedLines[0]);
        long recordDistance = extractLong(storedLines[1]);

        System.out.println(permutations(time, recordDistance));
    }

    private static long permutations(long time, long recordDistance) {
        double first = time / 2;
        double second = Math.sqrt(time * time - 4 * recordDistance) / -2;

        long result = (long) Math.ceil(second >= 0 ? first - second : first + second);

        return (time + 1) - (2 * result);
    }

    private static long extractLong(String line) {
        return Long.parseLong(line.split(": +")[1].replace(" ", ""));
    }
}