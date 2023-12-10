import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part1 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day10.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        char[][] storedLines = bufferedReader
            .lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }

    private static class Node {}
}
