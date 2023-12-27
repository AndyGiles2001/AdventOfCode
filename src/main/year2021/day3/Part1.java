package main.year2021.day3;

import java.io.BufferedReader;

import main.utils.InputOutputUtils;
import main.utils.MutableIntArray;

public class Part1 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        MutableIntArray accumulation = new MutableIntArray();

        reader.lines().forEach(line -> {
            if (!accumulation.isInitialized()) {
                accumulation.initialize(line.length());
            }

            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == '0') {
                    accumulation.decrementAt(i);
                } else if (c == '1') {
                    accumulation.incrementAt(i);
                } else {
                    throw new RuntimeException("Invalid character.");
                }
            }
        });

        InputOutputUtils.closeReader(reader);

        int gamma = 0;
        int epsilon = 0;
        for (int i = 0; i < accumulation.size(); i++) {
            int addend = 1;
            for (int j = 0; j < accumulation.size() - 1 - i; j++) {
                addend *= 2;
            }
            if (accumulation.get(i) > 0) {
                gamma += addend;
            } else {
                epsilon += addend;
            }
        }

        System.out.println(gamma);
        System.out.println(epsilon);
        System.out.println(gamma * epsilon);
    }
}
