package main.year2023.day15;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();
        String[] hashArr = line.split(",");
        int total = Arrays
            .stream(hashArr)
            .collect(Collectors.summingInt(Part1::computeHash));

        System.out.println(total);
    }

    private static int computeHash(String s) {
        int value = 0;
        for (char c : s.toCharArray()) {
            value += c;
            value *= 17;
            value %= 256;
        }
        return value;
    }
}
