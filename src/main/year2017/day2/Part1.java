package main.year2017.day2;

import java.io.BufferedReader;
import java.util.stream.Collectors;

import main.utils.ArrayUtils;
import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        int total = reader.lines().collect(Collectors.summingInt(Part1::getChecksum));
        InputOutputUtils.closeReader(reader);
        System.out.println(total);
    }

    private static int getChecksum(String line) {
        int[] values = ArrayUtils.splitToIntArray(line, "\t");
        return ArrayUtils.max(values) - ArrayUtils.min(values);
    }
}
