package main.year2020.day8;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

import main.utils.InputOutputUtils;
import main.utils.MutableInteger;

public class Part2 {

    private static final String ACCUMULATE = "acc";
    private static final String JUMP = "jmp";
    private static final String NOOP = "nop";

    public static void main(String[] args) {
        String[] instructions = InputOutputUtils.getStringArrayFromFile();

        int cursor = 0;
        while (true) {
            String currentInstruction = instructions[cursor];
            String flippedInstruction = flip(currentInstruction);

            if (flippedInstruction != null) {
                instructions[cursor] = flippedInstruction;

                int result = processInstructions(instructions);
                if (result != -1) {
                    System.out.println(result);
                    return;
                }

                instructions[cursor] = currentInstruction;
            }

            cursor++;
        }
    }

    private static String flip(String instruction) {
        StringJoiner sj = new StringJoiner(" ");

        if (instruction.startsWith(JUMP)) {
            sj.add(NOOP);
        } else if (instruction.startsWith(NOOP)) {
            sj.add(JUMP);
        } else {
            return null;
        }

        return sj.add(instruction.split(" ")[1]).toString();
    }

    private static int processInstructions(String[] instructions) {
        MutableInteger cursor = new MutableInteger();
        MutableInteger accumulator = new MutableInteger();
        Set<Integer> executedInstructions = new HashSet<>();
        while (true) {
            int cursorValue = cursor.get();

            if (cursorValue == instructions.length) {
                return accumulator.get();
            } else if (executedInstructions.contains(cursorValue)) {
                return -1;
            }

            executedInstructions.add(cursorValue);
            String instruction = instructions[cursorValue];
            processInstruction(instruction, cursor, accumulator);
        }
    }

    private static void processInstruction(String instruction, MutableInteger cursor, MutableInteger accumulator) {
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
