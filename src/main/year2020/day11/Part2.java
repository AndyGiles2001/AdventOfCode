package main.year2020.day11;

import java.lang.Math;

import main.utils.InputOutputUtils;

public class Part2 {

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
                    if (getOccupiedNeighbors(original, i, j) >= 5) {
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

        // right
        for (int i = 0; i < GRID_WIDTH - 1 - x; i++) {
            char c = grid[y][x + i + 1];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // left
        for (int i = 0; i < x; i++) {
            char c = grid[y][x - i - 1];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // down
        for (int i = 0; i < GRID_HEIGHT - 1 - y; i++) {
            char c = grid[y + i + 1][x];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // up
        for (int i = 0; i < y; i++) {
            char c = grid[y - i - 1][x];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // lower right
        for (int i = 0; i < Math.min(GRID_WIDTH - 1 - x, GRID_HEIGHT - 1 - y); i++) {
            char c = grid[y + i + 1][x + i + 1];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // lower left
        for (int i = 0; i < Math.min(x, GRID_HEIGHT - 1 - y); i++) {
            char c = grid[y + i + 1][x - i - 1];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // upper left
        for (int i = 0; i < Math.min(x, y); i++) {
            char c = grid[y - i - 1][x - i - 1];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        // upper right
        for (int i = 0; i < Math.min(GRID_WIDTH - 1 - x, y); i++) {
            char c = grid[y - i - 1][x + i + 1];
            if (c == OCCUPIED_SEAT) {
                total++;
                break;
            } else if (c == EMPTY_SEAT) {
                break;
            }
        }

        return total;
    }
}
