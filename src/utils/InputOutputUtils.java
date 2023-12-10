package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputOutputUtils {
    
    public static BufferedReader getBufferedReader(int year, int day) throws IOException {
        String filepath = String.format("../input/year%d/day%d.txt", year, day);
        FileReader fileReader = new FileReader(filepath);
        return new BufferedReader(fileReader);
    }
}