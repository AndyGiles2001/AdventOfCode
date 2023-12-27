package main.year2019.day2;

import java.io.IOException;
import java.util.Arrays;

import main.utils.ArrayUtils;
import main.utils.InputOutputUtils;

public class Part2 {

    private static final int TARGET_OUTPUT = 19690720;

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();
        int[] originalInstructions = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

        for (int noun = 0; noun < 99; noun++) {
            for (int verb = 0; verb < 99; verb++) {
                int[] instructions = ArrayUtils.copyOf(originalInstructions);

                instructions[1] = noun;
                instructions[2] = verb;

                int cursor = 0;
                while (true) {
                    int opcode = instructions[cursor];
                    if (opcode == 99) {
                        break;
                    }

                    int readIndex0 = instructions[cursor + 1];
                    int readIndex1 = instructions[cursor + 2];
                    int writeIndex = instructions[cursor + 3];

                    if (opcode == 1) {
                        instructions[writeIndex] = instructions[readIndex0] + instructions[readIndex1];
                    } else if (opcode == 2) {
                        instructions[writeIndex] = instructions[readIndex0] * instructions[readIndex1];
                    } else {
                        throw new RuntimeException("Invalid opcode.");
                    }

                    cursor += 4;
                }

                if (instructions[0] == TARGET_OUTPUT) {
                    System.out.println(
                        String.format("100 * %d + %d = %d", noun, verb, 100 * noun + verb)
                    );
                    return;
                }
            }
        }
    }
}
