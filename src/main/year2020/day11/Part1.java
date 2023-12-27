package main.year2020.day11;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final char FLOOR = '.';
    private static final char EMPTY_SEAT = 'L';
    private static final char OCCUPIED_SEAT = '#';

    private static int GRID_WIDTH;
    private static int GRID_HEIGHT;

    public static void main(String[] args) {
        char[][] curr = InputOutputUtils.getCharArrayArrayFromFile();
        GRID_WIDTH = curr[0].length;
        GRID_HEIGHT = curr.length;

        while (true) {
            char[][] next = nextIteration(curr);
            if (next == null) {
                break;
            } else {
                curr = next;
            }
        }

        System.out.println(countOccupiedSeats(curr));
    }

    private static char[][] nextIteration(char[][] original) {
        boolean anythingChanged = false;

        char[][] next = new char[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                char c = original[j][i];
                if (c == FLOOR) {
                    next[j][i] = FLOOR;
                } else if (c == EMPTY_SEAT) {
                    if (getOccupiedNeighbors(original, i, j) == 0) {
                        next[j][i] = OCCUPIED_SEAT;
                        anythingChanged = true;
                    } else {
                        next[j][i] = EMPTY_SEAT;
                    }
                } else if (c == OCCUPIED_SEAT) {
                    if (getOccupiedNeighbors(original, i, j) >= 4) {
                        next[j][i] = EMPTY_SEAT;
                        anythingChanged = true;
                    } else {
                        next[j][i] = OCCUPIED_SEAT;
                    }
                } else {
                    throw new RuntimeException();
                }
            }
        }

        return anythingChanged ? next : null;
    }

    private static int countOccupiedSeats(char[][] grid) {
        int total = 0;

        for (int i = 0; i < GRID_WIDTH; i++) {
            for (int j = 0; j < GRID_HEIGHT; j++) {
                if (grid[j][i] == OCCUPIED_SEAT) {
                    total++;
                }
            }
        }

        return total;
    }

    private static int getOccupiedNeighbors(char[][] grid, int x, int y) {
        int total = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }

                if (i < 0 || i >= GRID_WIDTH || j < 0 || j >= GRID_HEIGHT) {
                    continue;
                }

                if (grid[j][i] == OCCUPIED_SEAT) {
                    total++;
                }
            }
        }

        return total;
    }
}
