package main.year2017.day1;

import java.io.IOException;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String line = InputOutputUtils.getStringFromFile();

        int total = 0;
        for (int i = 0; i < line.length(); i++) {
            char curr = line.charAt(i);
            char next = line.charAt((i + 1) % line.length());

            if (curr == next) {
                total += curr - 48;
            }
        }

        System.out.println(total);
    }
}
