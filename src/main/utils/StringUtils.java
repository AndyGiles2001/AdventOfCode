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
}