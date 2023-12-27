package main.utils;

import java.util.Arrays;

public class ArrayUtils {
    
    public static int[] copyOf(int[] arr) {
        return Arrays.copyOf(arr, arr.length);
    }
}
