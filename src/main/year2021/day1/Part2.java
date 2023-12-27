package main.year2021.day1;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) {
        int[] lines = InputOutputUtils.getIntArrayFromFile();

        int total = 0;
        for (int i = 0; i < lines.length - 3; i++) {
            if (lines[i + 3] > lines[i]) {
                total++;
            }
        }

        System.out.println(total);
    }
}
