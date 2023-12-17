package main.year2023.day15;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;
import main.utils.Pair;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();
        String[] hashArr = line.split(",");
        ExposedMap map = new ExposedMap(256);
        Arrays
            .stream(hashArr)
            .forEach(map::execute);

        int total = map.computeTotal();
        System.out.println(total);
    }

    private static class ExposedMap {
        private static final Pattern pattern = Pattern.compile("([a-zA-Z]*)(=|-)([1-9]*)");
        private LinkedList<Pair<String, Integer>>[] boxes;
        private int numBoxes;

        @SuppressWarnings("unchecked")
        public ExposedMap(int numBoxes) {
            this.numBoxes = numBoxes;
            boxes = new LinkedList[numBoxes];
            for (int i = 0; i < numBoxes; i++) {
                boxes[i] = new LinkedList<>();
            }
        }

        private void execute(String instruction) {
            Matcher matcher = pattern.matcher(instruction);

            if (!matcher.find()) {
                throw new RuntimeException();
            }

            String key = matcher.group(1);
            char operation = matcher.group(2).charAt(0);

            if (operation == '=') {
                Integer value = Integer.valueOf(matcher.group(3));
                put(key, value);
            } else if (operation == '-') {
                remove(key);
            } else {
                throw new RuntimeException();
            }
        }

        public void put(String key, Integer value) {
            LinkedList<Pair<String, Integer>> box = getRelevantBox(key);
            for (Pair<String, Integer> entry : box) {
                if (key.equals(entry.getLeft())) {
                    entry.setRight(value);
                    return;
                }
            }

            box.add(new Pair<>(key, value));
        }

        public void remove(String key) {
            LinkedList<Pair<String, Integer>> box = getRelevantBox(key);
            for (Pair<String, Integer> entry : box) {
                if (key.equals(entry.getLeft())) {
                    box.remove(entry);
                    return;
                }
            }
        }

        public int computeTotal() {
            int total = 0;

            for (int i = 0; i < numBoxes; i++) {
                int j = 1;
                for (Pair<String, Integer> entry : boxes[i]) {
                    total += (i + 1) * j * entry.getRight();
                    j++;
                }
            }

            return total;
        }

        private LinkedList<Pair<String, Integer>> getRelevantBox(String key) {
            return boxes[computeHash(key)];
        }

        private int computeHash(String key) {
            int value = 0;
            for (char c : key.toCharArray()) {
                value += c;
                value *= 17;
                value %= numBoxes;
            }
            return value;
        }
    }
}
