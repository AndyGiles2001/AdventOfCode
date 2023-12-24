package main.year2020.day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.utils.InputOutputUtils;

public class Part2 {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = InputOutputUtils.getBufferedReaderFromFile();

        long validPasswords = reader
            .lines()
            .map(Password::new)
            .filter(password -> password.isValid())
            .count();

        reader.close();

        System.out.println(validPasswords);
    }

    private static class Password {

        private static final Pattern pattern = Pattern.compile("^(\\d+)-(\\d+) ([a-z]): ([a-z]+)$");

        private int firstPosition;
        private int secondPosition;
        private char characterToMatch;
        private String content;

        public Password(String serialized) {
            Matcher matcher = pattern.matcher(serialized);
            if (!matcher.find()) {
                throw new RuntimeException();
            }

            this.firstPosition = Integer.parseInt(matcher.group(1));
            this.secondPosition = Integer.parseInt(matcher.group(2));
            this.characterToMatch = matcher.group(3).charAt(0);
            this.content = matcher.group(4);
        }

        public boolean isValid() {
            return positionMatches(firstPosition) ^ positionMatches(secondPosition);
        }

        private boolean positionMatches(int position) {
            return content.charAt(position - 1) == characterToMatch;
        }
    }
}