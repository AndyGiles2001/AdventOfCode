package main.year2023.day2;

import java.io.BufferedReader; 
import java.io.IOException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import main.utils.InputOutputUtils;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReaderFromFile();

        Bag bag = new Bag(13, 14, 12);
        int total = bufferedReader
            .lines()
            .map(Game::new)
            .filter(game -> game.isPossible(bag))
            .collect(Collectors.summingInt(game -> game.getId()));

        bufferedReader.close();

        System.out.println(total);
    }

    private static class Game {

        private int id;
        private List<Handful> handfuls;

        public Game(String serialization) {
            String[] idAndHandfuls = serialization.split(": ");
            id = Integer.valueOf(idAndHandfuls[0].split(" ")[1]);

            handfuls = Arrays
                .stream(idAndHandfuls[1].split("; "))
                .map(Handful::new)
                .collect(Collectors.toList());
        }

        public int getId() {
            return id;
        }

        public boolean isPossible(Bag bag) {
            return handfuls.stream().allMatch(handful -> handful.isPossible(bag));
        }
    }

    private static class Handful {

        private static final String GREEN = "green";
        private static final String BLUE = "blue";
        private static final String RED = "red";

        private int green;
        private int blue;
        private int red;

        public Handful(String serialization) {
            String[] numbersAndColors = serialization.split(", ");
            for (String numberAndColor : numbersAndColors) {
                String[] separatedNumberAndColor = numberAndColor.split(" ");
                int number = Integer.valueOf(separatedNumberAndColor[0]);

                switch (separatedNumberAndColor[1]) {
                    case GREEN:
                        green = number;
                        break;
                    case BLUE:
                        blue = number;
                        break;
                    case RED:
                        red = number;
                        break;
                    default:
                        throw new RuntimeException("Invalid color!");
                }
            }
        }

        public boolean isPossible(Bag bag) {
            return green <= bag.getGreen()
                && blue <= bag.getBlue()
                && red <= bag.getRed();
        }
    }

    private static class Bag {

        private int green;
        private int blue;
        private int red;

        public Bag(int green, int blue, int red) {
            this.green = green;
            this.blue = blue;
            this.red = red;
        }

        public int getGreen() {
            return green;
        }

        public int getBlue() {
            return blue;
        }

        public int getRed() {
            return red;
        }
    }
}