package main.year2020.day3;

import java.io.IOException;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final char TREE = '#';

    public static void main(String[] args) throws IOException {
        char[][] lines = InputOutputUtils.getCharArrayArrayFromFile();
        int width = lines[0].length;

        int treesEncountered = 0;
        for (int i = 0; i < lines.length; i++) {
            int x = (i * 3) % width;
            char terrainEncountered = lines[i][x];
            if (terrainEncountered == TREE) {
                treesEncountered++;
            }
        }

        System.out.println(treesEncountered);
    }
}
