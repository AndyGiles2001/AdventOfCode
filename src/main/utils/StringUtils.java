package main.utils;

public class StringUtils {

    public static int countOccurences(String s, char target) {
        int total = 0;
        
        for (char c : s.toCharArray()) {
            if (c == target) {
                total++;
            }
        }

        return total;
    }

    public static int countOccurencesOfAny(String s, String targets) {
        int total = 0;

        char[] targetsArr = targets.toCharArray();

        for (char c : s.toCharArray()) {
            for (char target : targetsArr) {
                if (c == target) {
                    total++;
                }
            }
        }

        return total;
    }
}
