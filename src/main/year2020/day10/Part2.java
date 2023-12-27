package main.year2020.day10;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) {
        int[] originalArr = InputOutputUtils.getIntArrayFromFile();
        
        Arrays.sort(originalArr);

        int[] arr = getArrWithBookends(originalArr);

        System.out.println(getPermutations(arr));
    }

    private static long getPermutations(int[] arr) {
        return getPermutations(arr, 0, new HashMap<>());
    }

    private static long getPermutations(int[] arr, int index, Map<Integer, Long> memo) {
        if (index == arr.length - 1) {
            return 1;
        }

        if (memo.containsKey(index)) {
            return memo.get(index);
        }

        int currJoltage = arr[index];

        if (arr[index + 1] - currJoltage > 3) {
            throw new RuntimeException("Invalid input.");
        }

        long total = getPermutations(arr, index + 1, memo);

        if (index + 2 < arr.length && arr[index + 2] - currJoltage <= 3) {
            total += getPermutations(arr, index + 2, memo);

            if (index + 3 < arr.length && arr[index + 3] - currJoltage <= 3) {
                total += getPermutations(arr, index + 3, memo);
            }
        }

        memo.put(index, total);
        return total;
    }

    private static int[] getArrWithBookends(int[] original) {
        int[] arr = new int[original.length + 2];

        arr[0] = 0;
        for (int i = 0; i < original.length; i++) {
            arr[i + 1] = original[i];
        }
        arr[arr.length - 1] = arr[arr.length - 2] + 3;

        return arr;
    }
}
