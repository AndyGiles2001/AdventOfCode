package main.year2016.day5;

import java.io.IOException;

import main.utils.ByteUtils;
import main.utils.CryptographyUtils;
import main.utils.InputOutputUtils;

public class Part1 {

    private static final int REQUIRED_LEADING_ZEROES = 5;

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();

        StringBuilder passwordBuilder = new StringBuilder();
        int curr = 1;
        int found = 0;
        while (true) {
            String key = new StringBuilder()
                .append(line)
                .append(curr)
                .toString();

            byte[] hashedBytes = CryptographyUtils.hashWithMd5(key);

            if (CryptographyUtils.hasRequiredLeadingZeroes(hashedBytes, REQUIRED_LEADING_ZEROES)) {
                passwordBuilder.append(ByteUtils.getHexChar(hashedBytes[2], 1));
                if (++found == 8) {
                    System.out.println(passwordBuilder.toString());
                    return;
                }
            }

            curr++;
        }
    }
}
