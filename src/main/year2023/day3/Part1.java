package main.year2023.day3;

import java.io.IOException;

import main.utils.InputOutputUtils;

public class Part1 {
    public static void main(String[] args) throws IOException {
        String[] storedLines = InputOutputUtils.getStringArrayFromFile();

        int total = getTotal(storedLines);
        System.out.println(total);
    }

    private static int getTotal(String[] lines) {
        int total = 0;

        for (int i = 0; i < lines.length; i++) {
            String previousLine = i > 0 ? lines[i - 1] : null;
            String currentLine = lines[i];
            String nextLine = i < lines.length - 1 ? lines[i + 1] : null;

            int startOfNumber = -1;
            for (int j = 0; j < currentLine.length(); j++) {
                if (Character.isDigit(currentLine.charAt(j))) {
                    if (startOfNumber == -1) {
                        startOfNumber = j;
                    }
                } else {
                    if (startOfNumber != -1) {
                        if (isNextToSymbol(currentLine, startOfNumber, j, previousLine, nextLine)) {
                            int number = Integer.parseInt(currentLine.substring(startOfNumber, j));
                            total += number;
                        }

                        startOfNumber = -1;
                    }
                }
            }

            if (startOfNumber != -1) {
                if (isNextToSymbol(currentLine, startOfNumber, currentLine.length(), previousLine, nextLine)) {
                    int number = Integer.parseInt(currentLine.substring(startOfNumber));
                    total += number;
                }
            }
        }

        return total;
    }

    private static boolean isNextToSymbol(String line, int startIndex, int endIndex, String previousLine, String nextLine) {
        if (startIndex > 0 && isSymbol(line.charAt(startIndex - 1))) {
            return true;
        }

        if (endIndex < line.length() && isSymbol(line.charAt(endIndex))) {
            return true;
        }

        int exploreFrom = startIndex > 0 ? startIndex - 1 : 0;
        int exploreTo = endIndex < line.length() ? endIndex + 1 : line.length();

        if (previousLine != null) {
            for (int i = exploreFrom; i < exploreTo; i++) {
                if (isSymbol(previousLine.charAt(i))) {
                    return true;
                }
            }
        }

        if (nextLine != null) {
            for (int i = exploreFrom; i < exploreTo; i++) {
                if (isSymbol(nextLine.charAt(i))) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isSymbol(char c) {
        return !Character.isDigit(c) && c != '.';
    }
}