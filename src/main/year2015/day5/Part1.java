package main.year2015.day5;

import java.io.BufferedReader;

import main.utils.InputOutputUtils;
import main.utils.StringUtils;

public class Part1 {

    private static final String VOWELS = "aeiou";
    private static final String[] BLACKLIST = new String[]{"ab", "cd", "pq", "xy"};

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        long total = reader.lines().filter(Part1::isNice).count();
        InputOutputUtils.closeReader(reader);
        System.out.println(total);
    }

    private static boolean isNice(String s) {
        return StringUtils.countOccurencesOfAny(s, VOWELS) >= 3
            && hasConsecutiveCharacters(s)
            && !hasBannedSequence(s);
    }

    private static boolean hasConsecutiveCharacters(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasBannedSequence(String s) {
        for (String bannedString : BLACKLIST) {
            if (s.contains(bannedString)) {
                return true;
            }
        }

        return false;
    }
}
