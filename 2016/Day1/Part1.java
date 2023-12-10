import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.lang.Math;

public class Part1 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String line = bufferedReader.readLine();

        int direction = 0;
        int x = 0;
        int y = 0;

        String[] instructions = line.split(", ");

        for (int i = 0; i < instructions.length; i++) {
            String instruction = instructions[i];

            switch (instruction.charAt(0)) {
                case 'R':
                    direction++;
                    break;
                case 'L':
                    direction--;
                    break;
                default:
                    throw new RuntimeException(
                        String.format("Instruction %s does not have a valid turn direction.", instruction)
                    );
            }

            int distance = Integer.parseInt(instruction.substring(1));
            switch (direction % 4) {
                case 0:
                    y += distance;
                    break;
                case 1:
                    x += distance;
                    break;
                case 2:
                    y -= distance;
                    break;
                case 3:
                    x -= distance;
                    break;
            }
        }

        int totalDistance = Math.abs(x) + Math.abs(y);

        System.out.println(totalDistance);
    }
}
