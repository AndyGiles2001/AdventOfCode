package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InputOutputUtils {
    
    public static BufferedReader getBufferedReaderFromFile() throws IOException {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace.length < 3) {
            throw new RuntimeException("Cannot access caller's package name.");
        }
        String[] splitPackage = stackTrace[2].toString().split("\\.");
        
        String filepath = String.format("../input/%s/%s.txt", splitPackage[0], splitPackage[1]);
        FileReader fileReader = new FileReader(filepath);
        return new BufferedReader(fileReader);
    }
}