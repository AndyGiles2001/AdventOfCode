import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.Math;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Part2 {

    private static final int UP_LEFT = 0;
    private static final int UP = 1;
    private static final int UP_RIGHT = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 5;
    private static final int DOWN_LEFT = 6;
    private static final int DOWN = 7;
    private static final int DOWN_RIGHT = 8;

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day3.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        char[][] storedLines = bufferedReader
            .lines()
            .map(s -> s.toCharArray())
            .toArray(char[][]::new);

        bufferedReader.close();

        int total = getTotal(storedLines);

        System.out.println(total);
    }

    private static int getTotal(char[][] lines) {
        int total = 0;

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[i].length; j++) {
                if (lines[i][j] == '*') {
                    Set<Integer> directions = getDirections(i, j, lines);
                    if (directions.size() == 2) {
                        Iterator<Integer> iterator = directions.iterator();
                        int firstDirection = iterator.next();
                        int secondDirection = iterator.next();

                        int firstValue = getNumber(
                            lines[i - 1 + Math.floorDiv(firstDirection, 3)],
                            j - 1 + firstDirection % 3
                        );
                        int secondValue = getNumber(
                            lines[i - 1 + Math.floorDiv(secondDirection, 3)],
                            j - 1 + secondDirection % 3
                        );

                        total += firstValue * secondValue;
                    }
                }
            }
        }

        return total;
    }

    private static Set<Integer> getDirections(int lineIndex, int index, char[][] lines) {
        Set<Integer> directions = new HashSet<>();

        if (Character.isDigit(lines[lineIndex][index - 1])) {
            directions.add(LEFT);
        }

        if (Character.isDigit(lines[lineIndex][index + 1])) {
            directions.add(RIGHT);
        }

        if (!Character.isDigit(lines[lineIndex - 1][index])) {
            if (Character.isDigit(lines[lineIndex - 1][index - 1])) {
                directions.add(UP_LEFT);
            }

            if (Character.isDigit(lines[lineIndex - 1][index + 1])) {
                directions.add(UP_RIGHT);
            }
        } else {
            directions.add(UP);
        }

        if (!Character.isDigit(lines[lineIndex + 1][index])) {
            if (Character.isDigit(lines[lineIndex + 1][index - 1])) {
                directions.add(DOWN_LEFT);
            }

            if (Character.isDigit(lines[lineIndex + 1][index + 1])) {
                directions.add(DOWN_RIGHT);
            }
        } else {
            directions.add(DOWN);
        }

        return directions;
    }

    private static int getNumber(char[] arr, int index) {
        int left = index - 1;
        while (left >= 0 && Character.isDigit(arr[left])) {
            left--;
        }
        left++;

        int right = index + 1;
        while (right < arr.length && Character.isDigit(arr[right])) {
            right++;
        }
        right--;

        return Integer.parseInt(new String(arr, left, right + 1 - left));
    }
}