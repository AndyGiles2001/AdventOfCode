package main.year2018.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        int total = reader.lines().collect(Collectors.summingInt(Integer::parseInt));
        reader.close();

        System.out.println(total);
    }
}
