package main.year2020.day12;

import java.io.BufferedReader;

import main.utils.InputOutputUtils;
import main.utils.Point;

public class Part1 {

    private static final int EAST = 0;
    private static final int NORTH = 1;
    private static final int WEST = 2;
    private static final int SOUTH = 3;

    public static void main(String[] args) {

        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        Ferry ferry = new Ferry();
        reader.lines().forEach(line -> {
            char instruction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));

            switch (instruction) {
                case 'F':
                    ferry.goForward(amount);
                    break;
                case 'N':
                    ferry.goNorth(amount);
                    break;
                case 'E':
                    ferry.goEast(amount);
                    break;
                case 'S':
                    ferry.goSouth(amount);
                    break;
                case 'W':
                    ferry.goWest(amount);
                    break;
                case 'L':
                    ferry.turnLeft(amount);
                    break;
                case 'R':
                    ferry.turnRight(amount);
                    break;
                default:
                    throw new RuntimeException();
            }
        });
        InputOutputUtils.closeReader(reader);

        Point finalPosition = ferry.getPosition();
        System.out.println(finalPosition.manhattanDistance());
    }

    private static class Ferry {

        private Point position;
        private int direction;

        public Ferry() {
            position = new Point();
            direction = EAST;
        }

        public Point getPosition() {
            return position;
        }

        public void goForward(int distance) {
            switch (direction) {
                case NORTH:
                    goNorth(distance);
                    break;
                case EAST:
                    goEast(distance);
                    break;
                case SOUTH:
                    goSouth(distance);
                    break;
                case WEST:
                    goWest(distance);
                    break;
                default:
                    System.out.println(direction);
                    throw new RuntimeException();
            }
        }

        public void goNorth(int distance) {
            position.setY(position.getY() + distance);
        }

        public void goEast(int distance) {
            position.setX(position.getX() + distance);
        }

        public void goSouth(int distance) {
            position.setY(position.getY() - distance);
        }

        public void goWest(int distance) {
            position.setX(position.getX() - distance);
        }

        public void turnLeft(int degrees) {
            direction += degrees / 90;
            direction %= 4;
            direction += 4;
            direction %= 4;
        }

        public void turnRight(int degrees) {
            direction -= degrees / 90;
            direction %= 4;
            direction += 4;
            direction %= 4;
        }
    }
}
