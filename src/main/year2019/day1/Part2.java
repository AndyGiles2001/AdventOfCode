package main.year2019.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        int result = reader
            .lines()
            .map(Integer::parseInt)
            .collect(Collectors.summingInt(Part2::getFuelRequirement));
        reader.close();

        System.out.println(result);
    }

    private static int getFuelRequirement(int weight) {
        int fuelRequired = weight / 3 - 2;
        
        if (fuelRequired <= 0) {
            return 0;
        }

        return fuelRequired + getFuelRequirement(fuelRequired);
    }
}
