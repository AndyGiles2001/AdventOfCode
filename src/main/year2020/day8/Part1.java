package main.year2020.day8;

import java.util.HashSet;
import java.util.Set;

import main.utils.InputOutputUtils;
import main.utils.MutableInteger;

public class Part1 {

    private static final String ACCUMULATE = "acc";
    private static final String JUMP = "jmp";
    private static final String NOOP = "nop";

    public static void main(String[] args) {
        String[] instructions = InputOutputUtils.getStringArrayFromFile();

        MutableInteger cursor = new MutableInteger();
        MutableInteger accumulator = new MutableInteger();
        Set<Integer> executedInstructions = new HashSet<>();
        while (true) {
            int cursorValue = cursor.get();

            if (executedInstructions.contains(cursorValue)) {
                System.out.println(accumulator);
                return;
            }

            executedInstructions.add(cursorValue);
            String instruction = instructions[cursorValue];
            processInstruction(instruction, cursor, accumulator);
        }
    }

    public static void processInstruction(String instruction, MutableInteger cursor, MutableInteger accumulator) {
        String[] splitInstruction = instruction.split(" ");
        int amount = Integer.parseInt(splitInstruction[1]);

        switch (splitInstruction[0]) {
            case NOOP:
                cursor.increment();
                break;
            case ACCUMULATE:
                accumulator.add(amount);
                cursor.increment();
                break;
            case JUMP:
                cursor.add(amount);
                break;
            default:
                throw new RuntimeException("Invalid operation %s.");
        }
    }
}
