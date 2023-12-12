package main.utils;

public class Point {

    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public long manhattanDistance(Point other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
