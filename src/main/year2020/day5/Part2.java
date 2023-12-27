package main.year2020.day5;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        Set<Integer> seatIds = new HashSet<>();
        int minimumSeatId = Integer.MAX_VALUE;
        int maximumSeatId = -1;
        for (String line : lines) {
            int seatId = getSeatId(line);

            if (seatId < minimumSeatId) {
                minimumSeatId = seatId;
            } else if (seatId > maximumSeatId) {
                maximumSeatId = seatId;
            }

            seatIds.add(seatId);
        }

        for (int i = minimumSeatId + 1; i < maximumSeatId; i++) {
            if (!seatIds.contains(i)) {
                System.out.println(String.format("Missing Seat ID#%d", i));
                return;
            }
        }

        System.out.println("No gaps found.");
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
