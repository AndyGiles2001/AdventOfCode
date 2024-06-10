package main.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptographyUtils {

    public static byte[] hashWithMd5(String input) {
        try {
            return MessageDigest
                .getInstance("MD5")
                .digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean hasRequiredLeadingZeroes(byte[] arr, int requiredLeadingZeroes) {
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
