package main.year2023.day12;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.stream.Collectors;

import main.utils.InputOutputUtils;
import main.utils.StringUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        int result = bufferedReader
            .lines()
            .collect(Collectors.summingInt(Part1::getNumberOfPermutations));

        bufferedReader.close();

        System.out.println(result);
    }

    private static int getNumberOfPermutations(String s) {
        return 0;
    }
}
