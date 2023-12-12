package main.year2023.day10;

import java.io.IOException;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final char START = 'S';

    private static final int LEFT = 0;
    private static final int UP = 1;
    private static final int RIGHT = 2;
    private static final int DOWN = 3;

    public static void main(String[] args) throws IOException {
        char[][] grid = InputOutputUtils.getCharArrayArrayFromFile();

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
            System.out.println(String.format("Exploring (%d, %d)", nodeToExplore.getX(), nodeToExplore.getY()));
            explored.add(nodeToExplore.getEncoding(grid));

            Set<Node> neighbors = getUnvisitedNeighbors(grid, nodeToExplore, explored);

            System.out.println(neighbors.size());
            for (Node neighbor : neighbors) {
                if (neighbor.getContent() == START) {
                    return;
                } else {
                    visitedButUnexplored.push(neighbor);
                }
            }
        }

        throw new RuntimeException("Search finished, never found complete loop.");
    }

    private static Set<Node> getUnvisitedNeighbors(char[][] grid, Node node, Set<Integer> explored) {
        int x = node.getX();
        int y = node.getY();
        int distanceFromStart = node.getDistanceFromStart();

        Set<Node> unvisitedNeighbors = new HashSet<>();
        if (valid(x - 1, y, grid, LEFT, explored)) {
            unvisitedNeighbors.add(new Node(x - 1, y, grid[y][x - 1], node, distanceFromStart + 1));
        }

        if (valid(x, y - 1, grid, UP, explored)) {
            unvisitedNeighbors.add(new Node(x, y - 1, grid[y - 1][x], node, distanceFromStart + 1));
        }

        if (valid(x + 1, y, grid, RIGHT, explored)) {
            unvisitedNeighbors.add(new Node(x + 1, y, grid[y][x + 1], node, distanceFromStart + 1));
        }

        if (valid(x, y + 1, grid, DOWN, explored)) {
            unvisitedNeighbors.add(new Node(x, y + 1, grid[y + 1][x], node, distanceFromStart + 1));
        }

        return unvisitedNeighbors;
    }

    private static boolean valid(int x, int y, char[][] grid, int direction, Set<Integer> explored) {
        int gridWidth = grid[0].length;

        if (x < 0 || x >= gridWidth) {
            return false;
        }

        if (explored.contains(y * gridWidth + x)) {
            return false;
        }

        char content = grid[x][y];
        switch (direction) {
            case LEFT:
                return content == 'F' || content == 'L';
            case UP:
                return content == 'F' || content == '7';
            case RIGHT:
                return content == 'J' || content == '7';
            case DOWN:
                return content == 'J' || content == 'L';
        }

        return false;
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

        public int getDistanceFromStart() {
            return distanceFromStart;
        }

        public int getEncoding(char[][] grid) {
            return y * grid[0].length + x;
        }
    }
}
