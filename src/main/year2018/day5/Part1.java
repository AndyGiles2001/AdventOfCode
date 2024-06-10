package main.year2018.day5;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) {
        char[] polymerChain = InputOutputUtils.getCharArrayFromFile();


        System.out.println(polymerChain);
    }

    private static char[] alchemify(char[] original) {
        int length = original.length;
        char[] arr = new char[length];

        int i = 0;
        int j = 0;
        while (i < length - 1) {
            if (!Character.isLetter(original[i]) || !Character.isLetter(original[i + 1])) {
                arr[j] = original[i];
                i++;
                j++;
                continue;
            }

            if (Math.abs(original[i + 1] - original[i]) == 32) {
                i += 2;
            } else {
                arr[j] = original[i];
                i++;
                j++;
                continue;
            }
        }

        return arr;
    }
}
