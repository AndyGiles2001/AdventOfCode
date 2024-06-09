package main.year2020.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;
import main.utils.StringUtils;

public class Part2 {

    private static final Pattern maskPattern = Pattern.compile("^mask = ([01X]{36})$");
    private static final Pattern memoryPattern = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)$");

    private static final Map<Long, Long> memory = new HashMap<>();

    private static String bitmask;

    public static void main(String[] args) {
        InputOutputUtils
            .getBufferedReaderFromFile()
            .lines()
            .forEach(line -> {
                Matcher memoryMatcher = memoryPattern.matcher(line);
                if (memoryMatcher.find()) {
                    handleMemoryUpdate(
                        Integer.parseInt(memoryMatcher.group(1)),
                        Long.parseLong(memoryMatcher.group(2))
                    );
                    return;
                }

                Matcher maskMatcher = maskPattern.matcher(line);
                if (maskMatcher.find()) {
                    bitmask = maskMatcher.group(1);
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
        String addressSpace = applyBitmask(address);
        int numberOfUncertainties = StringUtils.countOccurences(addressSpace, 'X');
        long numberOfPermutations = 1 << numberOfUncertainties;

        for (int i = 0; i < numberOfPermutations; i++) {
            long specificAddress = specifyAddress(addressSpace, i);
            memory.put(specificAddress, value);
        }
    }

    private static long specifyAddress(String addressSpace, int permutation) {
        long result = 0L;
        long powerOfTwo = 1L;

        for (int i = 35; i >= 0; i--) {
            switch (addressSpace.charAt(i)) {
                case 'X':
                    if ((permutation & 1) > 0) {
                        result += powerOfTwo;
                    }
                    permutation >>= 1;
                    break;
                case '1':
                    result += powerOfTwo;
                    break;
            }

            powerOfTwo <<= 1;
        }

        return result;
    }

    private static String applyBitmask(int address) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            char maskBit = bitmask.charAt(i);
            if (maskBit == '0') {
                stringBuilder.append(((address & (1L << (35 - i))) > 0 ? '1' : '0'));
            } else {
                stringBuilder.append(maskBit);
            }
        }

        return stringBuilder.toString();
    }
}
