package year2023.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {

    private static String START_SUFFIX = "A";
    private static String DESTINATION_SUFFIX = "Z";

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day8.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        char[] instructions = bufferedReader.readLine().toCharArray();
        bufferedReader.readLine();

        Map<String, Pair> graph = new HashMap<>();

        bufferedReader
            .lines()
            .forEach(line -> deserializeIntoMap(graph, line));

        bufferedReader.close();

        List<String> currentNodes = graph
            .keySet()
            .stream()
            .filter(s -> s.endsWith(START_SUFFIX))
            .collect(Collectors.toList());

        int steps = 0;
        boolean done = false;
        while (!done) {
            for (char instruction : instructions) {
                currentNodes = currentNodes
                    .stream()
                    .map(currentNode -> takeStep(graph, currentNode, instruction))
                    .collect(Collectors.toList());

                steps++;

                if (currentNodes.stream().allMatch(currentNode -> currentNode.endsWith(DESTINATION_SUFFIX))) {
                    done = true;
                    break;
                }
            }
        }

        System.out.println(steps);
    }

    private static String takeStep(Map<String, Pair> graph, String currentNode, char instruction) {
        Pair nextPair = graph.get(currentNode);

        switch (instruction) {
            case 'L':
                return nextPair.getLeft();
            case 'R':
                return nextPair.getRight();
        }

        throw new RuntimeException(String.format("Invalid instruction %c.", instruction));
    }

    private static void deserializeIntoMap(Map<String, Pair> map, String serialized) {
        String[] splitSerialized = serialized.replaceAll(" |\\(|\\)", "").split("=");
        String[] destinations = splitSerialized[1].split(",");

        map.put(splitSerialized[0], new Pair(destinations[0], destinations[1]));
    }

    private static class Pair {

        private String left;
        private String right;

        public Pair(String left, String right) {
            this.left = left;
            this.right = right;
        }

        public String getLeft() {
            return left;
        }

        public String getRight() {
            return right;
        }

        public String toString() {
            return String.format("<%s,%s>", left, right);
        }
    }
}
