package year2023.day2;

import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Part2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day2.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        AtomicInteger total = new AtomicInteger();
        bufferedReader
            .lines()
            .map(Game::new)
            .map(game -> game.lowestPossible())
            .map(bag -> bag.getPower())
            .forEach(power -> total.getAndAdd(power));

        bufferedReader.close();
        
        System.out.println(total.get());
    }

    private static class Game {

        private int id;
        private List<Handful> handfuls;

        public Game(String serialization) {
            String[] idAndHandfuls = serialization.split(": ");
            id = Integer.valueOf(idAndHandfuls[0].split(" ")[1]);

            String[] separateHandfuls = idAndHandfuls[1].split("; ");
            handfuls = new ArrayList<>();
            for (String handful : separateHandfuls) {
                handfuls.add(new Handful(handful));
            }
        }

        public Bag lowestPossible() {
            int minGreen = 0;
            int minBlue = 0;
            int minRed = 0;

            for (Handful handful : handfuls) {
                int green = handful.getGreen();
                if (green > minGreen) {
                    minGreen = green;
                }

                int blue = handful.getBlue();
                if (blue > minBlue) {
                    minBlue = blue;
                }

                int red = handful.getRed();
                if (handful.getRed() > minRed) {
                    minRed = red;
                }
            }

            return new Bag(minGreen, minBlue, minRed);
        }

        public String toString() {
            String s = "Game " + String.valueOf(id);
            int index = 1;
            for (Handful handful : handfuls) {
                s += "\nHandful " + String.valueOf(index) + " - " + handful.toString();
                index++;
            }
            return s;
        }
    }

    private static class Handful {

        private int green;
        private int blue;
        private int red;

        public Handful(String serialization) {
            String[] numbersAndColors = serialization.split(", ");
            for (String numberAndColor : numbersAndColors) {
                String[] separatedNumberAndColor = numberAndColor.split(" ");
                int number = Integer.valueOf(separatedNumberAndColor[0]);
                String color = separatedNumberAndColor[1];

                if (color.equals("green")) {
                    green = number;
                } else if (color.equals("blue")) {
                    blue = number;
                } else if (color.equals("red")) {
                    red = number;
                } else {
                    throw new RuntimeException();
                }
            }
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

        public String toString() {
            return "Green: " + String.valueOf(green) + ", Blue: " + String.valueOf(blue) + ", Red: " + String.valueOf(red);
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

        public int getPower() {
            return green * blue * red;
        }
    }
}
