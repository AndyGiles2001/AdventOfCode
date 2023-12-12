package main.year2015.day2;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.Arrays;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        int total = bufferedReader
            .lines()
            .map(line -> new Present(line))
            .map(present -> present.getRibbonLength())
            .reduce(0, (a, b) -> a + b);

        bufferedReader.close();

        System.out.println(total);
    }

    private static class Present {

        private int[] dimensions = new int[3];

        public Present(String serialized) {
            String[] stringDimensions = serialized.split("x");
            for (int i = 0; i < 3; i++) {
                dimensions[i] = Integer.parseInt(stringDimensions[i]);
            }
            Arrays.sort(dimensions);
        }

        private int getRibbonLength() {
            int smallestPerimeter = dimensions[0] * 2 + dimensions[1] * 2;
            int volume = dimensions[0] * dimensions[1] * dimensions[2];

            return smallestPerimeter + volume;
        }
    }
}