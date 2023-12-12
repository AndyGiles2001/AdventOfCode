package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputOutputUtils {
    
    public static BufferedReader getBufferedReaderFromFile() throws IOException {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

        StackTraceElement initiator = stackTraceElements[stackTraceElements.length - 1];
        String[] splitPackage = initiator.toString().split("\\.");

        String filepath = System.getProperty("user.dir").endsWith("src")
            ? String.format("../input/%s/%s.txt", splitPackage[0], splitPackage[1])
            : String.format("input/%s/%s.txt", splitPackage[0], splitPackage[1]);

        FileReader fileReader = new FileReader(filepath);
        return new BufferedReader(fileReader);
    }

    public static String[] getStringArrayFromFile() throws IOException {
        BufferedReader bufferedReader = getBufferedReaderFromFile();
        String[] stringArr = bufferedReader.lines().toArray(String[]::new);
        bufferedReader.close();
        return stringArr;
    }

    public static char[][] getCharArrayArrayFromFile() throws IOException {
        BufferedReader bufferedReader = getBufferedReaderFromFile();
        char[][] charArrArr = bufferedReader
            .lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
        bufferedReader.close();
        return charArrArr;
    }

    public static String getStringFromFile() throws IOException {
        BufferedReader bufferedReader = getBufferedReaderFromFile();
        String firstLine = bufferedReader.readLine();
        bufferedReader.close();
        return firstLine;
    }
}
