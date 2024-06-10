package main.year2018.day2;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        for (int i = 0; i < lines.length - 1; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (offByOne(lines[i], lines[j])) {
                    System.out.println(getCommonCharacters(lines[i], lines[j]));
                    return;
                }
            }
        }
    }

    private static boolean offByOne(String a, String b) {
        int length = a.length();
        if (length != b.length()) {
            return false;
        }

        int differences = 0;
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                differences++;
            }
        }

        return differences == 1;
    }

    private static String getCommonCharacters(String a, String b) {
        if (a.length() != b.length()) {
            throw new RuntimeException();
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            if (c == b.charAt(i)) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }
}
