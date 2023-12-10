import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Part1 {

    private static final char START = 'S';

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day10.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        char[][] grid = bufferedReader
            .lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);

        int startingX = -1;
        int startingY = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == START) {
                    startingX = j;
                    startingY = i;
                    break;
                }
            }
        }

        if (startingX == -1 || startingY == -1) {
            throw new RuntimeException("Could not find start.");
        }

        Set<Integer> explored = new HashSet<>();
        Stack<Node> visitedButUnexplored = new Stack<>();
        visitedButUnexplored.push(new Node(startingX, startingY, START, null, 0));

        while (!visitedButUnexplored.isEmpty()) {
            Node nodeToExplore = visitedButUnexplored.pop();
            explored.add(nodeToExplore.getEncoding(grid));

            Set<Node> neighbors = getUnvisitedNeighbors(grid, nodeToExplore, explored);

            for (Node neighbor : neighbors) {
                if (neighbor.getContent() == START) {
                    return;
                } else {
                    visitedButUnexplored.add(neighbor);
                }
            }
        }

        throw new RuntimeException("Search finished, never found complete loop.");
    }

    private static Set<Node> getUnvisitedNeighbors(char[][] grid, Node node, Set<Integer> explored) {
        int gridWidth = grid[0].length;
        int gridHeight = grid.length;
        int x = node.getX();
        int y = node.getY();

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    break;
                }

                int xToSearch = x + i;
                int yToSearch = y + j;

                if (xToSearch < 0 || xToSearch >= gridWidth || yToSearch < 0 || yToSearch >= gridHeight) {
                    break;
                }

                if (explored.contains(yToSearch * gridWidth + xToSearch)) {
                    break;
                }
            }
        }

        return null;
    }

    private static class Node {

        private int x;
        private int y;

        private char content;
        private Node precursor;
        private int distanceFromStart;

        public Node(int x, int y, char content, Node precursor, int distanceFromStart) {
            this.x = x;
            this.y = y;
            this.content = content;
            this.precursor = precursor;
            this.distanceFromStart = distanceFromStart;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public char getContent() {
            return content;
        }

        public Node getPrecursor() {
            return precursor;
        }

        public int getEncoding(char[][] grid) {
            return y * grid[0].length + x;
        }
    }
}
