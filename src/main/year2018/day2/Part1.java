package main.year2018.day2;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import main.utils.InputOutputUtils;
import main.utils.MutableInteger;

public class Part1 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        MutableInteger twoOccurrences = new MutableInteger();
        MutableInteger threeOccurrences = new MutableInteger();
        reader.lines().forEach(line -> {
            Map<Character, Integer> occurrenceMap = getOccurrenceMap(line);
            if (occurrenceMap.containsValue(2)) {
                twoOccurrences.increment();
            }

            if (occurrenceMap.containsValue(3)) {
                threeOccurrences.increment();
            }
        });
        InputOutputUtils.closeReader(reader);
        System.out.println(twoOccurrences.get() * threeOccurrences.get());
    }

    private static Map<Character, Integer> getOccurrenceMap(String s) {
        Map<Character, Integer> occurrences = new HashMap<>();
        for (char c : s.toCharArray()) {
            occurrences.compute(c, (key, value) -> value == null ? 1 : value + 1);
        }
        return occurrences;
    }
}
