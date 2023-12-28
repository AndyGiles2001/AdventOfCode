package main.year2020.day15;

import java.util.HashMap;
import java.util.Map;

public class Part1 {

    private static final String INPUT = "6,3,15,13,1,0";
    private static final int TARGET_INDEX = 2020;

    public static void main(String[] args) {
        Map<Integer, Integer> sequence = new HashMap<>();
        int[] initialValues = getIntArrayFromString(INPUT);
        int prevValue = -1;
        for (int i = 0; i < initialValues.length; i++) {
            sequence.put(prevValue, i - 1);
            prevValue = initialValues[i];
        }

        for (int i = initialValues.length; i < TARGET_INDEX; i++) {
            int currValue = sequence.containsKey(prevValue)
                ? i - 1 - sequence.get(prevValue)
                : 0;

            sequence.put(prevValue, i - 1);
            prevValue = currValue;
        }

        System.out.println(prevValue);
    }

    private static int[] getIntArrayFromString(String s) {
        String[] splitString = s.split(",");

        int[] result = new int[splitString.length];
        for (int i = 0; i < splitString.length; i++) {
            result[i] = Integer.parseInt(splitString[i]);
        }

        return result;
    }
}
