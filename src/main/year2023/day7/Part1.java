package main.year2023.day7;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

import java.util.StringJoiner;

public class Part1 {

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
            Hand hand = hands.get(i);
            total += hand.getBid() * (i + 1);
        }

        System.out.println(total);
    }

    private static class Hand implements Comparable<Hand> {

        private String cards;
        private int bid;
        private int type;

        public Hand(String serialized) {
            String[] splitString = serialized.split(" ");
            cards = splitString[0];
            bid = Integer.parseInt(splitString[1]);

            Map<Character, Integer> counts = new HashMap<>();
            for (char c : cards.toCharArray()) {
                counts.put(c, counts.getOrDefault(c, 0) + 1);
            }

            switch (counts.size()) {
                case 1:
                    type = 6;
                    break;
                case 2:
                    type = counts.values().contains(4) ? 5 : 4;
                    break;
                case 3:
                    type = counts.values().contains(3) ? 3 : 2;
                    break;
                case 4:
                    type = 1;
                    break;
                case 5:
                    type = 0;
                    break;
                default:
                    throw new RuntimeException("Invalid size of counts map.");
            }
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

        private int getNumericalValue(char c) {
            if (Character.isDigit(c)) {
                return c - 48;
            }

            switch (c) {
                case 'T':
                    return 10;
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 14;
                default:
                    throw new RuntimeException("Invalid card character.");
            }
        }

        public String toString() {
            return new StringJoiner(" ")
                .add(cards)
                .add(String.valueOf(bid))
                .toString();
        }
    }
}
