package year2015.day1;

import java.io.IOException;

import utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();

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
        }

        System.out.println(floor);
    }
}
