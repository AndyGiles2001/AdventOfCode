package main.year2020.day5;

import java.io.BufferedReader;
import java.io.IOException;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        int highestSeatId = reader.lines().map(Part1::getSeatId).reduce(Integer::max).orElse(-1);
        reader.close();

        System.out.println(highestSeatId);
    }

    private static int getSeatId(String seatString) {
        String binaryString = seatString
            .replaceAll("F", "0")
            .replaceAll("B", "1")
            .replaceAll("L", "0")
            .replaceAll("R", "1");

        return Integer.parseInt(binaryString, 2);
    }
}
