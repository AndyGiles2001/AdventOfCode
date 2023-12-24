package main.year2020.day3;

import java.io.IOException;

import main.utils.InputOutputUtils;

public class Part2 {

    private static final char TREE = '#';

    public static void main(String[] args) throws IOException {
        char[][] lines = InputOutputUtils.getCharArrayArrayFromFile();

        long product = 1;

        product *= getTreesEncountered(lines, 1, 1);
        product *= getTreesEncountered(lines, 3, 1);
        product *= getTreesEncountered(lines, 5, 1);
        product *= getTreesEncountered(lines, 7, 1);
        product *= getTreesEncountered(lines, 1, 2);

        System.out.println(product);
    }

    private static int getTreesEncountered(char[][] lines, int right, int down) {
        int width = lines[0].length;

        int treesEncountered = 0;
        for (int y = 0; y < lines.length; y += down) {
            int x = (y * right / down) % width;

            char terrainEncountered = lines[y][x];
            if (terrainEncountered == TREE) {
                treesEncountered++;
            }
        }

        return treesEncountered;
    }
}
