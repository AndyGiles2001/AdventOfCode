package main.year2020.day9;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final int PREAMBLE_LENGTH = 25;

    public static void main(String[] args) {
        long[] arr = InputOutputUtils.getLongArrayFromFile();

        for (int i = PREAMBLE_LENGTH; i < arr.length; i++) {
            long target = arr[i];
            if (!canFindSum(arr, target, i - PREAMBLE_LENGTH, i)) {
                System.out.println(target);
                return;
            }
        }

        System.out.println("No invalid value found.");
    }

    private static boolean canFindSum(long[] arr, long target, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex - 1; i++) {
            for (int j = i + 1; j < endIndex; j++) {
                if (arr[i] + arr[j] == target) {
                    return true;
                }
            }
        }

        return false;
    }
}
