package main.year2020.day14;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;
import main.utils.StringUtils;

public class Part2 {

    private static final Pattern maskPattern = Pattern.compile("^mask = ([01X]{36})$");
    private static final Pattern memoryPattern = Pattern.compile("^mem\\[(\\d+)\\] = (\\d+)$");

    private static final Map<Long, Long> memory = new HashMap<>();

    private static String bitmask;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

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

        long result = memory
            .values()
            .stream()
            .collect(Collectors.summingLong(Long::longValue));

        long end = System.currentTimeMillis();

        System.out.println(String.format("%d - found in %dms.", result, end - start));
    }

    private static void handleMemoryUpdate(int address, long value) {
        String addressBitRepresentation = getBitStringFromLong(address, 36);
        String addressSpace = applyBitmask(addressBitRepresentation, bitmask);
        writeToAddressSpace(addressSpace, value);
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

    private static void writeToAddressSpace(String addressSpace, long value) {
        int numberOfUncertainties = StringUtils.countOccurences(addressSpace, 'X');
        long numberOfPermutations = exponentiate(2, numberOfUncertainties);

        for (int i = 0; i < numberOfPermutations; i++) {
            long specificAddress = specifyAddress(addressSpace, i, numberOfUncertainties);
            memory.put(specificAddress, value);
        }
    }

    private static Long specifyAddress(String uncertainAddress, int permutation, int numberOfPermutationBits) {
        String permutationBits = getBitStringFromLong(permutation, numberOfPermutationBits);

        long result = 0L;
        long powerOfTwo = 1L;

        int uncertaintiesPassed = 0;
        for (int i = uncertainAddress.length() - 1; i >= 0; i--) {
            switch (uncertainAddress.charAt(i)) {
                case 'X':
                    if (permutationBits.charAt(uncertaintiesPassed) == '1') {
                        result += powerOfTwo;
                    }
                    uncertaintiesPassed++;
                    break;
                case '1':
                    result += powerOfTwo;
                    break;
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

            if (maskBit == '0') {
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
