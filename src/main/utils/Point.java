package main.utils;

public class Point {

    protected int x;
    protected int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void increaseX(int deltaX) {
        this.x += deltaX;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseY(int deltaY) {
        this.y += deltaY;
    }

    public long manhattanDistance() {
        return Math.abs(x) + Math.abs(y);
    }

    public long manhattanDistance(Point other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }

    public int hashCode() {
        int hash = 19;

        hash = hash * 31 + x;
        hash = hash * 31 + y;
        
        return hash;
    }

    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Point otherPoint = (Point) other;

        return x == otherPoint.x && y == otherPoint.y;
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
