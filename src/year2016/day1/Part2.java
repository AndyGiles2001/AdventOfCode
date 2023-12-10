package year2016.day1;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReader(2016, 1);

        String line = bufferedReader.readLine();
        bufferedReader.close();

        int direction = 0;
        int x = 0;
        int y = 0;

        String[] instructions = line.split(", ");
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(new Coordinates(x, y));

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

    private static boolean checkIfComplete(int x, int y, Set<Coordinates> visitedCoordinates) {
        Coordinates coordinates = new Coordinates(x, y);
        if (visitedCoordinates.contains(coordinates)) {
            return true;
        }

        visitedCoordinates.add(coordinates);
        return false;
    }

    private static class Coordinates {

        private int x;
        private int y;

        public Coordinates(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
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

            Coordinates otherCoordinates = (Coordinates) other;

            return x == otherCoordinates.getX() && y == otherCoordinates.getY();
        }
    }
}
