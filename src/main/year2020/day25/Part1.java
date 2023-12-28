package main.year2020.day25;

import main.utils.InputOutputUtils;

public class Part1 {

    private static final int INITIAL_SUBJECT_NUMBER = 7;
    private static final int MODULO = 20201227;

    public static void main(String[] args) {
        String[] lines = InputOutputUtils.getStringArrayFromFile();
        int cardPublicKey = Integer.parseInt(lines[0]);
        int doorPublicKey = Integer.parseInt(lines[1]);

        int cardLoopNumber = getLoopNumber(INITIAL_SUBJECT_NUMBER, cardPublicKey);

        long encryptionKey = transform(doorPublicKey, cardLoopNumber);

        System.out.println(encryptionKey);
    }

    private static int getLoopNumber(int subjectNumber, int publicKey) {
        int loops = 0;
        int value = 1;

        while (value != publicKey) {
            value *= subjectNumber;
            value %= MODULO;
            loops++;
        }

        return loops;
    }

    private static long transform(int subjectNumber, int loopNumber) {
        long value = 1;

        for (int i = 0; i < loopNumber; i++) {
            value *= subjectNumber;
            value %= MODULO;
        }

        return value;
    }
}
