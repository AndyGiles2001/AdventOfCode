package main.year2020.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;
import main.utils.SetUtils;

public class Part1 {

    private static final int TARGET = 2020;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        Set<Integer> numbers = reader.lines().map(Integer::valueOf).collect(Collectors.toSet());
        reader.close();

        long start = System.currentTimeMillis();

        while (!numbers.isEmpty()) {
            Integer number = SetUtils.pop(numbers);
            int complement = TARGET - number;
            if (numbers.contains(complement)) {
                long end = System.currentTimeMillis();
                System.out.println(String.format("Found in %d ms.", end - start));
                
                int product = number * complement;
                System.out.println(String.format("%d * %d = %d", number, complement, product));
                return;
            }
        }

        System.out.println(String.format("No pair was found which adds to %d.", TARGET));
    }
}