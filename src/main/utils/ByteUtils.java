package main.utils;

public class ByteUtils {

    public static char getHexChar(byte b, int index) {
        return getHexChar(b, index, false);
    }

    public static char getHexChar(byte b, int index, boolean capitalized) {
        if (index == 0) {
            b &= 0xF0;
            b >>= 4;
        } else if (index == 1) {
            b &= 0x0F;
        } else {
            throw new IllegalArgumentException("Index must be 0 or 1.");
        }

        if (b < 10) {
            return (char) (b + 48);
        } else {
            return capitalized ? (char) (b + 55) : (char) (b + 87);
        }
    }
}
