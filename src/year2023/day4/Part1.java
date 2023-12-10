package year2023.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        Integer result = bufferedReader
            .lines()
            .map(line -> new Card(line))
            .map(card -> card.getPower())
            .reduce(0, (a, b) -> a + b);
        
        bufferedReader.close();

        System.out.println(result);
    }

    private static class Card {

        private Set<Integer> winningNumbers = new HashSet<>();
        private Set<Integer> myNumbers = new HashSet<>();

        public Card(String serialized) {
            String allNumbers = serialized
                .replace("  ", " ")
                .split(": ")[1];

            String[] separatedNumbers = allNumbers.split(" \\| ");

            String[] winningArr = separatedNumbers[0].split(" ");
            String[] myArr = separatedNumbers[1].split(" ");

            for (int i = 0; i < winningArr.length; i++) {
                winningNumbers.add(Integer.valueOf(winningArr[i]));
            }

            for (int i = 0; i < myArr.length; i++) {
                myNumbers.add(Integer.valueOf(myArr[i]));
            }
        }

        public int getPower() {
            Set<Integer> myWinningNumbers = intersection(winningNumbers, myNumbers);

            if (myWinningNumbers.isEmpty()) {
                return 0;
            }

            int product = 1;
            for (int i = 0; i < myWinningNumbers.size() - 1; i++) {
                product *= 2;
            }
    
            return product;
        }

        private static <T> Set<T> intersection(Set<T> a, Set<T> b) {
            return a
                .stream()
                .filter(b::contains)
                .collect(Collectors.toSet());
        }
    }
}
