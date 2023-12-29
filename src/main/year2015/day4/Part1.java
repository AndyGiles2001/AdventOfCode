package main.year2015.day4;

import main.utils.CrypotgraphyUtils;
import main.utils.InputOutputUtils;

public class Part1 {

    private static final int REQUIRED_ZEROES = 5;

    public static void main(String[] args) {
        String secretKey = InputOutputUtils.getStringFromFile();

        int numberSuffix = 1;
        while (true) {
            StringBuilder keyBuilder = new StringBuilder()
                .append(secretKey)
                .append(numberSuffix);
            
            byte[] hashedBytes = CrypotgraphyUtils.hashWithMd5(keyBuilder.toString());

            if (hasRequiredLeadingZeroes(hashedBytes, REQUIRED_ZEROES)) {
                System.out.println(numberSuffix);
                return;
            }

            numberSuffix++;
        }
    }

    private static boolean hasRequiredLeadingZeroes(byte[] arr, int requiredLeadingZeroes) {
        int half = requiredLeadingZeroes / 2;
        for (int i = 0; i < half; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        if (requiredLeadingZeroes % 2 == 0) {
            return true;
        }

        return arr[half] >> 4 == 0;
    }
}
