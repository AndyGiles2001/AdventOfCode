package main.year2016.day1;

import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import main.utils.InputOutputUtils;
import main.utils.Point;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();

        int direction = 0;
        int x = 0;
        int y = 0;

        String[] instructions = line.split(", ");
        Set<Point> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(new Point(x, y));

        for (int i = 0; i < instructions.length; i++) {
            String instruction = instructions[i];

            switch (instruction.charAt(0)) {
                case 'R':
                    direction++;
                    break;
                case 'L':
                    direction--;
                    break;
            }
            
            boolean complete = false;
            int distance = Integer.parseInt(instruction.substring(1));
            switch (direction % 4) {
                case 0:
                    for (int j = 0; j < distance; j++) {
                        y++;
                        if (complete = checkIfComplete(x, y, visitedCoordinates)) {
                            break;
                        }
                    }
                    break;
                case 1:
                    for (int j = 0; j < distance; j++) {
                        x++;
                        if (complete = checkIfComplete(x, y, visitedCoordinates)) {
                            break;
                        }
                    }
                    break;
                case 2:
                    for (int j = 0; j < distance; j++) {
                        y--;
                        if (complete = checkIfComplete(x, y, visitedCoordinates)) {
                            break;
                        }
                    }
                    break;
                case 3:
                    for (int j = 0; j < distance; j++) {
                        x--;
                        if (complete = checkIfComplete(x, y, visitedCoordinates)) {
                            break;
                        }
                    }
                    break;
            }

            if (complete) {
                break;
            }
        }

        System.out.println(String.format("[%d, %d]", x, y));
    }

    private static boolean checkIfComplete(int x, int y, Set<Point> visitedCoordinates) {
        Point coordinates = new Point(x, y);
        if (visitedCoordinates.contains(coordinates)) {
            return true;
        }

        visitedCoordinates.add(coordinates);
        return false;
    }
}
