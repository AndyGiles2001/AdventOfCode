import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

public class Part1 {

    private static String START_NODE = "AAA";
    private static String DESTINATION_NODE = "ZZZ";

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

        int steps = 0;
        String currentNode = START_NODE;
        boolean done = false;
        while (!done) {
            for (char instruction : instructions) {
                Pair nextPair = graph.get(currentNode);
                switch (instruction) {
                    case 'L':
                        currentNode = nextPair.getLeft();
                        break;
                    case 'R':
                        currentNode = nextPair.getRight();
                        break;
                    default:
                        throw new RuntimeException(String.format("Invalid instruction %c.", instruction));
                }

                steps++;

                if (DESTINATION_NODE.equals(currentNode)) {
                    done = true;
                    break;
                }
            }
        }

        System.out.println(steps);
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
