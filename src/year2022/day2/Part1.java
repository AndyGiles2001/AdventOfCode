package year2022.day2;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.stream.Collectors;

import utils.InputOutputUtils;

public class Part1 {

    private static final int ROCK_SCORE = 1;
    private static final int PAPER_SCORE = 2;
    private static final int SCISSORS_SCORE = 3;

    private static final int DRAW = 3;
    private static final int WIN = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = InputOutputUtils.getBufferedReader(2022, 2);

        int result = bufferedReader
            .lines()
            .collect(Collectors.summingInt(Part1::getScore));

        bufferedReader.close();

        System.out.println(result);
    }

    private static int getScore(String round) {
        String[] splitRound = round.split(" ");
        int yourChoice = getNumerical(splitRound[0].charAt(0));
        int myChoice = getNumerical(splitRound[1].charAt(0));

        int score = getOutcome(myChoice, yourChoice) + myChoice;
        System.out.println(String.format("%s -> %d", round, score));
        return score;
    }

    private static int getNumerical(char c) {
        if (c == 'A' || c == 'X') {
            return ROCK_SCORE;
        }

        if (c == 'B' || c == 'Y') {
            return PAPER_SCORE;
        }

        if (c == 'C' || c == 'Z') {
            return SCISSORS_SCORE;
        }

        throw new RuntimeException();
    }

    private static int getOutcome(int myChoice, int yourChoice) {
        if (myChoice < 1 || myChoice > 3 || yourChoice < 1 || yourChoice > 3) {
            throw new RuntimeException();
        }

        if (myChoice == yourChoice) {
            return DRAW;
        }

        if (myChoice > yourChoice) {
            return myChoice - yourChoice == 1 ? WIN : 0;
        }

        return yourChoice - myChoice == 1 ? 0 : WIN;
    }
}
