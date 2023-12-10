import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Part2 {

    public static void main(String[] args) throws IOException {
        FileReader fileReader = new FileReader("../Input/Day2.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int total = bufferedReader
            .lines()
            .map(line -> new Present(line))
            .map(present -> present.getRibbonLength())
            .reduce(0, (a, b) -> a + b);

        System.out.println(total);
    }

    private static class Present {

        private int[] dimensions = new int[3];

        public Present(String serialized) {
            String[] stringDimensions = serialized.split("x");
            for (int i = 0; i < 3; i++) {
                dimensions[i] = Integer.parseInt(stringDimensions[i]);
            }
            Arrays.sort(dimensions);
        }

        private int getWrappingArea() {
            int[] areas = new int[]{dimensions[0] * dimensions[1], dimensions[0] * dimensions[2], dimensions[1] * dimensions[2]};
            return areas[0] * 3 + areas[1] * 2 + areas[2] * 2;
        }

        private int getRibbonLength() {
            int smallestPerimeter = dimensions[0] * 2 + dimensions[1] * 2;
            int volume = dimensions[0] * dimensions[1] * dimensions[2];

            return smallestPerimeter + volume;
        }
    }
}