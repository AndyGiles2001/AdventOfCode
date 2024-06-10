package main.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputOutputUtils {
    
    public static BufferedReader getBufferedReaderFromFile() {
        try {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

            StackTraceElement initiator = stackTraceElements[stackTraceElements.length - 1];
            String[] splitPackage = initiator.toString().split("\\.");

            String filepath = System.getProperty("user.dir").endsWith("/src")
                ? String.format("../input/%s/%s.txt", splitPackage[1], splitPackage[2])
                : String.format("input/%s/%s.txt", splitPackage[1], splitPackage[2]);

            FileReader fileReader = new FileReader(filepath);
            return new BufferedReader(fileReader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getStringArrayFromFile() {
        try {
            BufferedReader bufferedReader = getBufferedReaderFromFile();
            String[] stringArr = bufferedReader.lines().toArray(String[]::new);
            bufferedReader.close();
            return stringArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static char[][] getCharArrayArrayFromFile() {
        try {
            BufferedReader bufferedReader = getBufferedReaderFromFile();
            char[][] charArrArr = bufferedReader
                .lines()
                .map(String::toCharArray)
                .toArray(char[][]::new);
            bufferedReader.close();
            return charArrArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] getIntArrayFromFile() {
        try {
            BufferedReader bufferedReader = getBufferedReaderFromFile();
            int[] intArr = bufferedReader.lines().mapToInt(Integer::parseInt).toArray();
            bufferedReader.close();
            return intArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static long[] getLongArrayFromFile() {
        try {
            BufferedReader bufferedReader = getBufferedReaderFromFile();
            long[] longArr = bufferedReader.lines().mapToLong(Long::parseLong).toArray();
            bufferedReader.close();
            return longArr;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringFromFile() {
        try {
            BufferedReader bufferedReader = getBufferedReaderFromFile();
            String firstLine = bufferedReader.readLine();
            bufferedReader.close();
            return firstLine;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static char[] getCharArrayFromFile() {
        return getStringFromFile().toCharArray();
    }

    public static int getIntFromFile() {
        String s = getStringFromFile();
        return Integer.parseInt(s);
    }

    public static void closeReader(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
