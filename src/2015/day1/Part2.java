import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Part2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        int basementIndex = getBasementIndex(line);

        System.out.println(basementIndex);
    }

    private static int getBasementIndex(String line) {
        int floor = 0;

        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '(':
                    floor++;
                    break;
                case ')':
                    floor--;
                    break;
            }

            if (floor == -1) {
                return i + 1;
            }
        }

        return -1;
    }
}
