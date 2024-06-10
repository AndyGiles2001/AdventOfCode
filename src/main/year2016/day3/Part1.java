package main.year2016.day3;

import java.io.BufferedReader;
import java.io.IOException;

import main.utils.ArrayUtils;
import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        long total = reader.lines().filter(Part1::isValidTriangle).count();
        InputOutputUtils.closeReader(reader);
        System.out.println(total);
    }

    private static boolean isValidTriangle(String line) {
        int[] arr = ArrayUtils.splitToIntArray(line.trim(), " +");

        return arr[0] < arr[1] + arr[2]
            && arr[1] < arr[0] + arr[2]
            && arr[2] < arr[0] + arr[1];
    }
}
