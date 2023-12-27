package main.utils;

public class Point {

    protected int x;
    protected int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public long manhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public long manhattanDistance(Point other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
