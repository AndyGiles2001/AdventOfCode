package main.year2017.day4;

import java.io.BufferedReader;
import java.util.Arrays;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        long total = reader.lines().filter(Part2::isValid).count();
        InputOutputUtils.closeReader(reader);
        System.out.println(total);
    }

    private static boolean isValid(String line) {
        String[] splitLine = line.split(" ");
        return splitLine.length == Arrays.stream(splitLine).map(Part2::alphabetize).distinct().count();
    }

    private static String alphabetize(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }
}
