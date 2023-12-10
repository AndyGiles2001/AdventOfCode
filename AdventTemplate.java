import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AdventTemplate {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/DayXX.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String[] storedLines = bufferedReader
            .lines()
            .toArray(String[]::new);
    }
}
