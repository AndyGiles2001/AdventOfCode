package main.year2020.day14;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
                    handleMaskUpdate(maskMatcher.group(1));
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

        long result = memory
            .values()
            .stream()
            .collect(Collectors.summingLong(Long::longValue));

        System.out.println(result);
    }

    private static void handleMaskUpdate(String newMask) {
        bitmask = newMask;
    }

    private static void handleMemoryUpdate(int address, long value) {
        String bitRepresentation = getBitStringFromLong(value, 36);
        String maskedBitRepresentation = applyBitmask(bitRepresentation, bitmask);
        long maskedValue = getLongFromBitString(maskedBitRepresentation);

        memory.put(address, maskedValue);
    }

    private static String getBitStringFromLong(long l, int numBits) {
        StringBuilder stringBuilder = new StringBuilder();

        long powerOfTwo = exponentiate(2, numBits - 1);

        while (powerOfTwo > 0) {
            if (l >= powerOfTwo) {
                stringBuilder.append('1');
                l -= powerOfTwo;
            } else {
                stringBuilder.append('0');
            }

            powerOfTwo /= 2;
        }

        return stringBuilder.toString();
    }

    private static long getLongFromBitString(String s) {
        long result = 0L;

        long powerOfTwo = 1L;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1') {
                result += powerOfTwo;
            }

            powerOfTwo *= 2;
        }

        return result;
    }

    private static String applyBitmask(String bitRepresentation, String bitmask) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < 36; i++) {
            char realBit = bitRepresentation.charAt(i);
            char maskBit = bitmask.charAt(i);

            if (maskBit == 'X') {
                stringBuilder.append(realBit);
            } else {
                stringBuilder.append(maskBit);
            }
        }

        return stringBuilder.toString();
    }

    private static long exponentiate(int base, int exponent) {
        long result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }
}
