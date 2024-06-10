package main.year2020.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final Pattern maskPattern = Pattern.compile("^mask = ([01X]{36})$");
    private static final Pattern memoryPattern = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)$");

    private static final Map<Integer, Long> memory = new HashMap<>();

    private static String bitmask;

    public static void main(String[] args) {
        InputOutputUtils
            .getBufferedReaderFromFile()
            .lines()
            .forEach(line -> {
                Matcher maskMatcher = maskPattern.matcher(line);
                if (maskMatcher.find()) {
                    bitmask = maskMatcher.group(1);
                    return;
                }

                Matcher memoryMatcher = memoryPattern.matcher(line);
                if (memoryMatcher.find()) {
                    handleMemoryUpdate(
                        Integer.parseInt(memoryMatcher.group(1)),
                        Long.parseLong(memoryMatcher.group(2))
                    );
                    return;
                }

                throw new RuntimeException("Could not identify pattern for line: " + line);
            });

        long result = 0L;
        for (long value : memory.values()) {
            result += value;
        }

        System.out.println(result);
    }

    private static void handleMemoryUpdate(int address, long value) {
        for (int i = 0; i < 36; i++) {
            char maskBit = bitmask.charAt(i);

            if (maskBit == '1') {
                value |= 1L << (35 - i);
            } else if (maskBit == '0') {
                value &= ~(1L << (35 - i));
            }
        }

        memory.put(address, value);
    }
}
