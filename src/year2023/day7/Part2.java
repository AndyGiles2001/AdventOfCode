package year2023.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        List<Hand> hands = bufferedReader
            .lines()
            .map(Hand::new)
            .sorted()
            .collect(Collectors.toList());

        bufferedReader.close();

        int total = 0;
        for (int i = 0; i < hands.size(); i++) {
            total += hands.get(i).getBid() * (i + 1);
        }

        System.out.println(total);
    }

    private static class Hand implements Comparable<Hand> {

        private static final int MULTIPLIER = 10;
        private static final int CARDS_PER_HAND = 5;
        
        private static final char JOKER = 'J';

        private String cards;
        private int bid;
        private int type;

        public Hand(String serialized) {
            String[] splitString = serialized.split(" ");
            cards = splitString[0];
            bid = Integer.parseInt(splitString[1]);
            type = calculateType();
        }

        public int getBid() {
            return bid;
        }

        public int compareTo(Hand other) {
            if (type != other.type) {
                return type - other.type;
            }

            for (int i = 0; i < cards.length(); i++) {
                int thisValue = getNumericalValue(cards.charAt(i));
                int otherValue = getNumericalValue(other.cards.charAt(i));

                if (thisValue != otherValue) {
                    return thisValue - otherValue;
                }
            }

            throw new RuntimeException("Cards are equivalent.");
        }

        private int calculateType() {
            Map<Character, Integer> counts = new HashMap<>();
            int jokerCount = 0;
            for (char c : cards.toCharArray()) {
                if (c == JOKER) {
                    jokerCount++;
                } else {
                    counts.put(c, counts.getOrDefault(c, 0) + 1);
                }
            }

            List<Integer> groups = new ArrayList<>(counts.values());
            Collections.sort(groups, Collections.reverseOrder());

            return getScore(groups, jokerCount);
        }

        private int getScore(List<Integer> groups, int jokerCount) {
            if (groups.isEmpty()) {
                return CARDS_PER_HAND * MULTIPLIER;
            }

            int initialValue = (groups.get(0) + jokerCount) * MULTIPLIER;

            if (groups.size() == 1) {
                return initialValue;
            }
            
            return initialValue + groups.get(1);
        }

        private int getNumericalValue(char c) {
            return "J23456789TQKA".indexOf(c);
        }
    }
}
