package main.year2015.day5;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();
        long total = reader.lines().filter(Part2::isNice).count();
        InputOutputUtils.closeReader(reader);
        System.out.println(total);
    }

    private static boolean isNice(String s) {
        boolean hasSandwich = false;
        int twins = 0;
        Map<Character, Set<Character>> followersByPrecursors = new HashMap<>();

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char curr = s.charAt(i);
            if (i > 0) {
                Set<Character> followers = followersByPrecursors.computeIfAbsent(
                    s.charAt(i - 1), k -> new HashSet<>()
                );

                if (!followers.add(curr)) {
                    twins++;
                }
            }

            if (i < length - 2 && curr == s.charAt(i + 2)) {
                hasSandwich = true;
                if (curr == s.charAt(i + 1)) {
                    twins--;
                }
            }
        }

        return twins > 0 && hasSandwich;
    }
}
