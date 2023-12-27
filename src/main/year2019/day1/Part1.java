package main.year2019.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        int result = reader.lines().collect(Collectors.summingInt(Part1::getFuelRequirement));
        reader.close();

        System.out.println(result);
    }

    private static int getFuelRequirement(String line) {
        return Integer.parseInt(line) / 3 - 2;
    }
}
