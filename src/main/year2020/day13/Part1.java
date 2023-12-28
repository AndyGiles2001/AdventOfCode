package main.year2020.day13;

import java.util.Arrays;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        int departureTime = Integer.parseInt(lines[0]);
        int[] busIds = Arrays
            .stream(lines[1].split(","))
            .filter(id -> !id.equals("x"))
            .mapToInt(Integer::parseInt)
            .toArray();

        Arrays.sort(busIds);

        for (int i = 0; i < busIds[0]; i++) {
            for (int busId : busIds) {
                if ((departureTime + i) % busId == 0) {
                    System.out.println(
                        String.format(
                            "Bus %d is available after %d minutes. (%d)",
                            busId,
                            i,
                            busId * i
                        )
                    );

                    return;
                }
            }
        }

        throw new RuntimeException("The laws of arithmetic have been broken.");
    }
}
