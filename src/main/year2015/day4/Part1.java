package main.year2015.day4;

import main.utils.CryptographyUtils;
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
            
            byte[] hashedBytes = CryptographyUtils.hashWithMd5(keyBuilder.toString());

            if (CryptographyUtils.hasRequiredLeadingZeroes(hashedBytes, REQUIRED_ZEROES)) {
                System.out.println(numberSuffix);
                return;
            }

            numberSuffix++;
        }
    }
}
