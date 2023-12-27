package main.utils;

import java.lang.Math;

import main.utils.Point;

public class OrthogonalLineSegment {

    private static boolean HORIZONTAL = false;
    private static boolean VERTICAL = true;
    
    private boolean axis;
    private int primaryCoordinate;
    private int lowerSecondaryCoordinate;
    private int upperSecondaryCoordinate;

    public OrthogonalLineSegment(Point a, Point b) {
        if (a.getY() == b.getY()) {
            axis = HORIZONTAL;
            primaryCoordinate = a.getY();
            lowerSecondaryCoordinate = Math.min(a.getX(), b.getX());
            upperSecondaryCoordinate = Math.max(a.getX(), b.getX());
        } else if (a.getX() == b.getX()) {
            axis = VERTICAL;
            primaryCoordinate = a.getX();
            lowerSecondaryCoordinate = Math.min(a.getY(), b.getY());
            upperSecondaryCoordinate = Math.max(a.getY(), b.getY());
        } else {
            throw new RuntimeException();
        }
    }

    public boolean getAxis() {
        return axis;
    }

    public int getPrimaryCoordinate() {
        return primaryCoordinate;
    }

    public int getLowerSecondaryCoordinate() {
        return lowerSecondaryCoordinate;
    }

    public int getUpperSecondaryCoordinate() {
        return upperSecondaryCoordinate;
    }

    public Point getIntersectionPoint(OrthogonalLineSegment other) {
        if (!intersects(other)) {
            return null;
        }

        return axis == VERTICAL
            ? new Point(primaryCoordinate, other.getPrimaryCoordinate())
            : new Point(other.getPrimaryCoordinate(), primaryCoordinate);
    }

    private boolean intersects(OrthogonalLineSegment other) {
        return axis != other.getAxis()
            && primaryCoordinate > other.getLowerSecondaryCoordinate()
            && primaryCoordinate < other.getUpperSecondaryCoordinate()
            && other.getPrimaryCoordinate() > lowerSecondaryCoordinate
            && other.getPrimaryCoordinate() < upperSecondaryCoordinate;
    }
}
