import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

public class Part1 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day3.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        int currX = 0;
        int currY = 0;

        Set<House> houses = new HashSet<>();
        houses.add(new House(currX, currY));

        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '^':
                    currY++;
                    break;
                case '>':
                    currX++;
                    break;
                case 'v':
                    currY--;
                    break;
                case '<':
                    currX--;
                    break;
            }

            houses.add(new House(currX, currY));
        }
        System.out.println(houses.size());
    }

    private static class House {

        private int x;
        private int y;

        public House(int x, int y) {
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

            House otherHouse = (House) other;

            return x == otherHouse.getX() && y == otherHouse.getY();
        }
    }
}
