package main.year2020.day12;

import java.io.BufferedReader;

import main.utils.ComplexNumber;
import main.utils.InputOutputUtils;
import main.utils.Point;

public class Part2 {

    private static final ComplexNumber IMAGINARY_UNIT = new ComplexNumber(0, 1);

    public static void main(String[] args) {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        Ferry ferry = new Ferry();
        Waypoint waypoint = new Waypoint(10, 1);
        reader.lines().forEach(line -> {
            char instruction = line.charAt(0);
            int amount = Integer.parseInt(line.substring(1));

            switch (instruction) {
                case 'F':
                    ferry.moveToWaypoint(waypoint, amount);
                    break;
                case 'N':
                    waypoint.goNorth(amount);
                    break;
                case 'E':
                    waypoint.goEast(amount);
                    break;
                case 'S':
                    waypoint.goSouth(amount);
                    break;
                case 'W':
                    waypoint.goWest(amount);
                    break;
                case 'L':
                    waypoint.rotateLeft(amount);
                    break;
                case 'R':
                    waypoint.rotateRight(amount);
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

        public Ferry() {
            position = new Point();
        }

        public Point getPosition() {
            return position;
        }

        public void moveToWaypoint(Waypoint waypoint, int amount) {
            int deltaX = waypoint.getX() * amount;
            int deltaY = waypoint.getY() * amount;

            position.increaseX(deltaX);
            position.increaseY(deltaY);
        }
    }

    private static class Waypoint {

        private ComplexNumber position;

        public Waypoint(int amountEast, int amountNorth) {
            position = new ComplexNumber(amountEast, amountNorth);
        }

        public int getX() {
            return position.getReal();
        }

        public int getY() {
            return position.getImaginary();
        }

        public void goNorth(int distance) {
            position.addImaginary(distance);
        }

        public void goEast(int distance) {
            position.addReal(distance);
        }

        public void goSouth(int distance) {
            position.subtractImaginary(distance);
        }

        public void goWest(int distance) {
            position.subtractReal(distance);
        }

        public void rotateLeft(int degrees) {
            rotate(degrees);
        }

        public void rotateRight(int degrees) {
            rotate(-degrees);
        }

        private void rotate(int degrees) {
            for (int i = 0; i < normalize(degrees) / 90; i++) {
                position.multiply(IMAGINARY_UNIT);
            }
        }

        private int normalize(int x) {
            x %= 360;
            x += 360;
            x %= 360;
            return x;
        }
    }
}
