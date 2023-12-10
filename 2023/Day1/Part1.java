import java.io.BufferedReader; 
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class Part1 {
    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int total = bufferedReader.lines().collect(Collectors.summingInt(Part1::getCalibration));

        System.out.println(total);
    }

    private static int getCalibration(String line) {
        return getFirstDigit(line) * 10 + getLastDigit(line);
    }

    private static int getFirstDigit(String s) {
        for (char c : s.toCharArray()) {
            if (c >= 48 && c <= 57) {
                return c - 48;
            }
        }

        throw new RuntimeException("No digits found!");
    }

    private static int getLastDigit(String s) {
        return getFirstDigit(new StringBuilder(s).reverse().toString());
    }
}