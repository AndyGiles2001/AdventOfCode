package main.year2020.day9;

import main.utils.InputOutputUtils;

public class Part2 {

    private static final int PREAMBLE_LENGTH = 25;

    public static void main(String[] args) {
        long[] arr = InputOutputUtils.getLongArrayFromFile();

        long invalidNumber = -1;
        for (int i = PREAMBLE_LENGTH; i < arr.length; i++) {
            long target = arr[i];
            if (!canFindSum(arr, target, i - PREAMBLE_LENGTH, i)) {
                invalidNumber = target;
                break;
            }
        }

        if (invalidNumber == -1) {
            throw new RuntimeException("No invalid value found.");
        }

        for (int start = 0; start < arr.length; start++) {
            long total = 0;
            for (int i = start; i < arr.length; i++) {
                total += arr[i];
                if (total == invalidNumber) {
                    long minValue = Long.MAX_VALUE;
                    long maxValue = -1;

                    for (int j = start; j <= i; j++) {
                        long value = arr[j];
                        if (value > maxValue) {
                            maxValue = value;
                        }

                        if (value < minValue) {
                            minValue = value;
                        }
                    }

                    System.out.println(minValue + maxValue);

                    return;
                } else if (total > invalidNumber) {
                    break;
                }
            }
        }

        System.out.println("No valid contiguous range found.");
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
