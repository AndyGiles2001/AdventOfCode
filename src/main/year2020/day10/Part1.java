package main.year2020.day10;

import java.util.Arrays;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) {
        int[] arr = InputOutputUtils.getIntArrayFromFile();
        
        Arrays.sort(arr);

        int numSingleJumps = 0;
        int numTripleJumps = 1;
        for (int i = 0; i < arr.length; i++) {
            int previousJolts = i == 0 ? 0 : arr[i - 1];
            int currentJolts = arr[i];
            int jumpLength = currentJolts - previousJolts;
            if (jumpLength == 1) {
                numSingleJumps++;
            } else if (jumpLength == 3) {
                numTripleJumps++;
            }
        }

        System.out.println(numSingleJumps * numTripleJumps);
    }
}
