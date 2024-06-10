package main.year2016.day3;

import java.io.BufferedReader;
import java.io.IOException;

import main.utils.ArrayUtils;
import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String[] lines = InputOutputUtils.getStringArrayFromFile();
        int numLines = lines.length;
        int[][] triplets = new int[numLines][3];
        for (int i = 0; i < numLines; i++) {
            triplets[i] = ArrayUtils.splitToIntArray(lines[i].trim(), " +");
        }

        int total = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < numLines; j += 3) {
                if (isValidTriangle(triplets[j][i], triplets[j + 1][i], triplets[j + 2][i])) {
                    total ++;
                }
            }
        }

        System.out.println(total);
    }



    private static boolean isValidTriangle(int a, int b, int c) {
        return a < b + c
            && b < a + c
            && c < a + b;
    }
}
