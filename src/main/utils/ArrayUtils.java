package main.utils;

import java.util.Arrays;
import java.util.StringJoiner;

public class ArrayUtils {
    
    public static int[] copyOf(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }

    public static String intArrayToString(int[] arr) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        
        for (int value : arr) {
            stringJoiner.add(String.valueOf(value));
        }

        return String.format("[%s]", stringJoiner);
    }
}
