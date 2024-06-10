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

    public static int[] splitToIntArray(String s, String splitter) {
        String[] stringArr = s.split(splitter);
        int arrSize = stringArr.length;
        int[] intArr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            intArr[i] = Integer.parseInt(stringArr[i]);
        }
        return intArr;
    }

    public static int min(int[] arr) {
        int[] copy = copyOf(arr);
        Arrays.sort(copy);
        return copy[0];
    }

    public static int max(int[] arr) {
        int[] copy = copyOf(arr);
        Arrays.sort(copy);
        return copy[copy.length - 1];
    }
}
