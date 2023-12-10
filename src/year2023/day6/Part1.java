package year2023.day6;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        String[] storedLines = bufferedReader
            .lines()
            .toArray(String[]::new);
        
        bufferedReader.close();

        List<Integer> times = extractIntegers(storedLines[0]);
        List<Integer> recordDistances = extractIntegers(storedLines[1]);

        int total = 1;
        for (int i = 0; i < times.size(); i++) {
            int time = times.get(i);
            int recordDistance = recordDistances.get(i);

            total *= permutations(time, recordDistance);
        }

        System.out.println(total);
    }

    private static int permutations(int time, int recordDistance) {
        double first = time / 2;
        double second = Math.sqrt(time * time - 4 * recordDistance) / -2;

        int result = (int) Math.ceil(second >= 0 ? first - second : first + second);

        return (time + 1) - (2 * result);
    }

    private static List<Integer> extractIntegers(String line) {
        return Arrays
            .stream(line.split(": +")[1].split(" +"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
