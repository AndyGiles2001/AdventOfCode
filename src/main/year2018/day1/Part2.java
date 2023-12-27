package main.year2018.day1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        int totalFrequency = 0;
        Set<Integer> foundFrequencies = new HashSet<>();
        foundFrequencies.add(totalFrequency);

        int[] frequencies = InputOutputUtils.getIntArrayFromFile();
        while (true) {
            for (int frequency : frequencies) {
                totalFrequency += frequency;
                if (foundFrequencies.contains(totalFrequency)) {
                    System.out.println(totalFrequency);
                    return;
                }
                foundFrequencies.add(totalFrequency);
            }
        }
    }
}
