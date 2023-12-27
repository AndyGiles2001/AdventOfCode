package main.year2021.day2;

import java.io.BufferedReader;

import main.utils.InputOutputUtils;
import main.utils.MutableInteger;

public class Part1 {

    private static final String FORWARD = "forward";
    private static final String UP = "up";
    private static final String DOWN = "down";

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        MutableInteger x = new MutableInteger();
        MutableInteger depth = new MutableInteger();

        reader.lines().forEach(line -> {
            String[] splitLine = line.split(" ");
            String direction = splitLine[0];
            int distance = Integer.parseInt(splitLine[1]);

            if (FORWARD.equals(direction)) {
                x.add(distance);
            } else if (UP.equals(direction)) {
                depth.subtract(distance);
            } else if (DOWN.equals(direction)) {
                depth.add(distance);
            } else {
                throw new RuntimeException("Invalid direction.");
            }
        });

        int product = x.get() * depth.get();
        System.out.println(product);
    }
}
