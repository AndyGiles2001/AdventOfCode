package year2023.day11;

import java.io.IOException;

import java.lang.Math;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import utils.InputOutputUtils;
import utils.SetUtils;

public class Part2 {

    private static final char GALAXY = '#';
    private static final int EXPANSION_RATE = 1000000;

    public static void main(String[] args) throws IOException {
        char[][] grid = InputOutputUtils.getCharArrayArrayFromFile();
        int gridWidth = grid[0].length;
        int gridHeight = grid.length;

        Set<Integer> emptyRows = getCountingSet(gridHeight);
        Set<Integer> emptyColumns = getCountingSet(gridWidth);
        
        Set<Galaxy> galaxies = new HashSet<>();

        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {
                if (grid[j][i] == GALAXY) {
                    galaxies.add(new Galaxy(i, j));
                    emptyRows.remove(j);
                    emptyColumns.remove(i);
                }
            }
        }

        galaxies.forEach(galaxy -> galaxy.adjustCoordinates(emptyRows, emptyColumns, EXPANSION_RATE));

        long total = 0;
        while (!galaxies.isEmpty()) {
            Galaxy galaxy = SetUtils.pop(galaxies);

            total += galaxies
                .stream()
                .collect(Collectors.summingLong(otherGalaxy -> otherGalaxy.getDistanceFrom(galaxy)));
        }

        System.out.println(total);
    }

    private static Set<Integer> getCountingSet(int n) {
        return IntStream
            .range(0, n)
            .boxed()
            .collect(Collectors.toSet());
    }

    private static class Galaxy {

        private int x;
        private int y;

        public Galaxy(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void adjustCoordinates(Set<Integer> emptyRows, Set<Integer> emptyColumns, int expansion) {
            x += emptyColumns.stream().filter(columnIndex -> columnIndex < x).count() * (expansion - 1);
            y += emptyRows.stream().filter(rowIndex -> rowIndex < y).count() * (expansion - 1);
        }

        public long getDistanceFrom(Galaxy other) {
            return Math.abs(x - other.x) + Math.abs(y - other.y);
        }
    }
}
