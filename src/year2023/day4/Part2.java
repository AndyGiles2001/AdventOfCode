package year2023.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day4.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Pattern pattern = Pattern.compile(".*: (.*) \\| (.*)");

        List<Integer> matches = bufferedReader
            .lines()
            .map(line -> new Card(line, pattern))
            .map(card -> card.getMatchingNumbers())
            .collect(Collectors.toList());

        bufferedReader.close();

        int[] arr = new int[matches.size()];
        int total = 0;
        for (int i = 0; i < arr.length; i++) {
            int copies = arr[i] + 1;
            for (int j = i + 1; j <= i + matches.get(i); j++) {
                arr[j] += copies;
            }
            total += copies;
        }

        System.out.println(total);
    }

    private static class Card {

        private Set<Integer> winningNumbers;
        private Set<Integer> myNumbers;

        public Card(String serialized, Pattern pattern) {
            Matcher matcher = pattern.matcher(serialized.replaceAll(" +", " "));

            if (matcher.find()) {
                winningNumbers = Arrays
                    .asList(matcher.group(1).split(" "))
                    .stream()
                    .map(numberString -> Integer.valueOf(numberString))
                    .collect(Collectors.toSet());

                myNumbers = Arrays
                    .asList(matcher.group(2).split(" "))
                    .stream()
                    .map(numberString -> Integer.valueOf(numberString))
                    .collect(Collectors.toSet());
            } else {
                throw new RuntimeException("Cannot parse card.");
            }
        }

        public int getMatchingNumbers() {
            return myNumbers
                .stream()
                .filter(winningNumbers::contains)
                .collect(Collectors.counting())
                .intValue();
        }
    }
}
