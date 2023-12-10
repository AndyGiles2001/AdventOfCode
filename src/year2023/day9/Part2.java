import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day9.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int total = bufferedReader
            .lines()
            .collect(Collectors.summingInt(Part2::extrapolate));

            bufferedReader.close();

        System.out.println(total);
    }

    private static int extrapolate(String s) {
        List<Integer> values = parse(s);
        List<Integer> firstValues = new ArrayList<>();

        while (!allZeroes(values)) {
            firstValues.add(values.get(0));
            values = reduce(values);
        }

        int runningDifference = 0;
        for (int i = firstValues.size() - 1; i >= 0; i--) {
            runningDifference = firstValues.get(i) - runningDifference;
        }

        return runningDifference;
    }

    private static List<Integer> reduce(List<Integer> values) {
        List<Integer> reducedValues = new ArrayList<>();
        for (int i = 0; i < values.size() - 1; i++) {
            reducedValues.add(values.get(i + 1) - values.get(i));
        }
        return reducedValues;
    }

    private static boolean allZeroes(List<Integer> values) {
        return values.stream().allMatch(value -> value == 0);
    }

    private static List<Integer> parse(String s) {
        return Arrays
            .stream(s.split(" +"))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }
}
