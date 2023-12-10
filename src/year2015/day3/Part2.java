package year2015.day3;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

import utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReader(2015, 3);

        String line = bufferedReader.readLine();
        bufferedReader.close();

        int[] currX = new int[]{0, 0};
        int[] currY = new int[]{0, 0};

        Set<House> houses = new HashSet<>();
        houses.add(new House(currX[0], currY[0]));

        for (int i = 0; i < line.length(); i++) {
            int turn = i % 2;
            switch (line.charAt(i)) {
                case '^':
                    currY[turn]++;
                    break;
                case '>':
                    currX[turn]++;
                    break;
                case 'v':
                    currY[turn]--;
                    break;
                case '<':
                    currX[turn]--;
                    break;
            }

            houses.add(new House(currX[turn], currY[turn]));
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