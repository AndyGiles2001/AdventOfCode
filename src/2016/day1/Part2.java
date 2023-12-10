import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.Math;

import java.util.HashSet;
import java.util.Set;

public class Part2 {

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        FileReader fileReader = new FileReader("../Input/Day1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        int direction = 0;
        int x = 0;
        int y = 0;

        String[] instructions = line.split(", ");
        Set<Coordinates> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(new Coordinates(x, y));

        for (int i = 0; i < instructions.length; i++) {
            Coordinates currentCoordinates = new Coordinates(x, y);

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

        long endTime = System.currentTimeMillis();

        System.out.println(String.format("[%d, %d]", x, y));
        System.out.println(String.format("%d ms", endTime - startTime));
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
