package edu.fh.cs.compgeometry.stramm.primitives;

import com.sun.javafx.geom.Vec2d;

/**
 * Created by femy on 4/16/15.
 */
public class SimpleLineSegment implements LineSegment {

    public static final double THRESHOLD = 1e-6; //Math.pow(10.0, -12.0);

    private final Vec2d point1;

    private final Vec2d point2;

    public SimpleLineSegment(Vec2d point1, Vec2d point2) {
        this.point1 = point1;
        this.point2 = point2;
    }


    @Override
    public Vec2d getPoint1() {
        return point1;
    }

    @Override
    public Vec2d getPoint2() {
        return point2;
    }

    @Override
    public double ccw(Vec2d point) {
        double ccw = this.point1.y * point.x -
        this.point2.y * point.x +
        this.point2.x * point.y -
        this.point1.x * point.y -
        this.point1.y * point2.x +
        this.point1.x * point2.y;
        return Math.abs(ccw) >= THRESHOLD ? ccw : 0;
    }

    @Override
    public boolean isCrossing(LineSegment lineSegment) {
        double ccwOtherOne = this.ccw(lineSegment.getPoint1());
        double ccwOtherTwo = this.ccw(lineSegment.getPoint2());
        if (ccwOtherOne == 0 && ccwOtherTwo == 0) {
            return isColinearOverlapping(lineSegment);
        }
        boolean checkSideSelf = ccwOtherOne * ccwOtherTwo <= 0.0;
        boolean checkSideGiven = lineSegment.ccw(this.getPoint1()) * lineSegment.ccw(this.getPoint2()) <= 0.0;

        return checkSideSelf && checkSideGiven;
    }

    private boolean isColinearOverlapping(LineSegment lineSegment) {
        return isOnLine(lineSegment.getPoint1()) || isOnLine(lineSegment.getPoint2());
    }

    private boolean isOnLine(Vec2d point) {
        if (this.ccw(point) != 0) {
            return false;
        }
        double lengthSelf = this.getPoint1().distance(this.getPoint2());
        double lengthToPoint = this.getPoint1().distance(point);

        return lengthSelf * lengthToPoint >= 0 && Math.abs(lengthToPoint) <= Math.abs(lengthSelf);
    }

    @Override
    public String toString() {
        return point1.x + ":" + point1.y + "->" + point2.x + ":" + point2.y;
    }
}
