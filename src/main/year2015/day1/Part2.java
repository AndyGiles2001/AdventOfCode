package main.year2015.day1;

import java.io.IOException;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();

        int basementIndex = getBasementIndex(line);

        System.out.println(basementIndex);
    }

    private static int getBasementIndex(String line) {
        int floor = 0;

        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '(':
                    floor++;
                    break;
                case ')':
                    floor--;
                    break;
            }

            if (floor == -1) {
                return i + 1;
            }
        }

        return -1;
    }
}
