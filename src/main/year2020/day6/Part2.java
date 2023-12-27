package main.year2020.day6;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;

import main.utils.InputOutputUtils;
import main.utils.MutableBoolean;
import main.utils.MutableInteger;
import main.utils.SetUtils;

public class Part2 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        Set<Character> affirmatives = new HashSet<>();
        MutableInteger total = new MutableInteger();
        MutableBoolean flag = new MutableBoolean(true);
        
        reader.lines().forEach(line -> {
            if (line.isEmpty()) {
                total.add(affirmatives.size());
                affirmatives.clear();
                flag.set();
                return;
            }

            if (flag.get()) {
                SetUtils.addAll(affirmatives, line.toCharArray());
                flag.clear();
            } else {
                SetUtils.retainAll(affirmatives, line.toCharArray());
            }
        });
        InputOutputUtils.closeReader(reader);

        total.add(affirmatives.size());
        System.out.println(total);
    }
}
