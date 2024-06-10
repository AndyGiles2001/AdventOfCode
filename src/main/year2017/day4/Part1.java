package main.year2017.day4;

import java.io.BufferedReader;
import java.util.Arrays;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        long total = reader.lines().filter(Part1::hasNoDuplicates).count();
        InputOutputUtils.closeReader(reader);
        System.out.println(total);
    }

    private static boolean hasNoDuplicates(String line) {
        String[] splitLine = line.split(" ");
        return splitLine.length == Arrays.stream(splitLine).distinct().count();
    }
}
