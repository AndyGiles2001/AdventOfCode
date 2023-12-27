package main.year2019.day4;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) {
        String line = InputOutputUtils.getStringFromFile();
        String[] splitLine = line.split("-");
        int lowerBound = Integer.parseInt(splitLine[0]);
        int upperBound = Integer.parseInt(splitLine[1]);

        int numValid = 0;
        for (int i = lowerBound; i <= upperBound; i++) {
            if (isValid(i)) {
                numValid++;
            }
        }

        System.out.println(numValid);
    }

    private static boolean isValid(int num) {
        String numString = String.valueOf(num);
        return hasDouble(numString) && notDecreasing(numString);
    }

    private static boolean hasDouble(String numString) {
        for (int i = 0; i < numString.length() - 1; i++) {
            char curr = numString.charAt(i);
            char next = numString.charAt(i + 1);
            if (curr == next) {
                return true;
            }
        }

        return false;
    }

    private static boolean notDecreasing(String numString) {
        for (int i = 0; i < numString.length() - 1; i++) {
            char curr = numString.charAt(i);
            char next = numString.charAt(i + 1);
            if (next < curr) {
                return false;
            }
        }

        return true;
    }
}
