package main.year2018.day3;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final Pattern pattern = Pattern.compile("^#\\d+ @ (\\d+),(\\d+): (\\d+)x(\\d+)$");

    public static void main(String[] args) {
        Map<Integer, Integer> usesBySquareInch = new HashMap<>();

        InputOutputUtils
            .getBufferedReaderFromFile()
            .lines()
            .forEach(line -> handleLine(line, usesBySquareInch));

        long result = usesBySquareInch.values().stream().filter(uses -> uses > 1).count();
        System.out.println(result);
    }

    private static void handleLine(String line, Map<Integer, Integer> usesBySquareInch) {
        FabricRegion fabricRegion = getFabricRegion(line);
        int left = fabricRegion.getLeft();
        int top = fabricRegion.getTop();
        int width = fabricRegion.getWidth();
        int height = fabricRegion.getHeight();

        for (int i = left; i < left + width; i++) {
            for (int j = top; j < top + height; j++) {
                usesBySquareInch.compute(j * 1000 + i, (k, v) -> v == null ? 1 : v + 1);
            }
        }
    }

    private static FabricRegion getFabricRegion(String line) {
        Matcher matcher = pattern.matcher(line);
        if (!matcher.find()) {
            throw new RuntimeException(String.format("Cannot match line: %s", line));
        }

        return new FabricRegion(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4));
    }

    private static class FabricRegion {

        private int left;
        private int top;
        private int width;
        private int height;

        public FabricRegion(String left, String top, String width, String height) {
            this.left = Integer.parseInt(left);
            this.top = Integer.parseInt(top);
            this.width = Integer.parseInt(width);
            this.height = Integer.parseInt(height);
        }

        public int getLeft() {
            return left;
        }

        public int getTop() {
            return top;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }
    }
}