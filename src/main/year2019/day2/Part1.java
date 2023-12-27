package main.year2019.day2;

import java.io.IOException;
import java.util.Arrays;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();
        int[] instructions = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();

        instructions[1] = 12;
        instructions[2] = 2;

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

        System.out.println(instructions[0]);
    }
}
