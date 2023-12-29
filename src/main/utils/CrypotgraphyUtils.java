package main.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrypotgraphyUtils {
    public static byte[] hashWithMd5(String input) {
        try {
            return MessageDigest
                .getInstance("MD5")
                .digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
