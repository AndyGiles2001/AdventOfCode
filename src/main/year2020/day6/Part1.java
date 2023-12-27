package main.year2020.day6;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;

import main.utils.InputOutputUtils;
import main.utils.MutableInteger;
import main.utils.SetUtils;

public class Part1 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        Set<Character> affirmatives = new HashSet<>();
        MutableInteger total = new MutableInteger();
        reader.lines().forEach(line -> {
            if (line.isEmpty()) {
                total.add(affirmatives.size());
                affirmatives.clear();
                return;
            }

            SetUtils.addAll(affirmatives, line.toCharArray());
        });
        InputOutputUtils.closeReader(reader);

        total.add(affirmatives.size());
        System.out.println(total);
    }
}
