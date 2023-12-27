package main.year2019.day3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.utils.InputOutputUtils;
import main.utils.OrthogonalLineSegment;
import main.utils.Point;

public class Part1 {

    public static void main(String[] args) throws IOException {
        String[] lines = InputOutputUtils.getStringArrayFromFile();

        List<Point> path0 = getPoints(lines[0]);
        List<Point> path1 = getPoints(lines[1]);

        Set<Point> intersectionPoints = new HashSet<>();

        for (int i = 0; i < path0.size() - 1; i++) {
            OrthogonalLineSegment lineSegment0 = new OrthogonalLineSegment(path0.get(i), path0.get(i + 1));
            for (int j = 0; j < path1.size() - 1; j++) {
                OrthogonalLineSegment lineSegment1 = new OrthogonalLineSegment(path1.get(j), path1.get(j + 1));
                Point intersectionPoint = lineSegment0.getIntersectionPoint(lineSegment1);
                if (intersectionPoint != null) {
                    intersectionPoints.add(intersectionPoint);
                }
            }
        }
        
        long shortestDistance = intersectionPoints
            .stream()
            .mapToLong(point -> point.manhattanDistance())
            .min()
            .orElse(-1);

        System.out.println(shortestDistance);
    }

    private static List<Point> getPoints(String line) {
        String[] splitLine = line.split(",");

        int x = 0;
        int y = 0;

        List<Point> points = new ArrayList<>();
        points.add(new Point(x, y));

        for (String instruction : splitLine) {
            char direction = instruction.charAt(0);
            int numSteps = Integer.parseInt(instruction.substring(1));
            switch (direction) {
                case 'U':
                    y += numSteps;
                    break;
                case 'R':
                    x += numSteps;
                    break;
                case 'D':
                    y -= numSteps;
                    break;
                case 'L':
                    x -= numSteps;
                    break;
                default:
                    throw new RuntimeException();
            }

            points.add(new Point(x, y));
        }

        return points;
    }
}
