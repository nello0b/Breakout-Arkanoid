// 318844677 Netanel Berkovits
package Geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.List;


/**
 * Name: Geometry.Geometry.Line Class
 * Description: A Geometry.Geometry.Line object that represent a line by 2 points.
 */
public class Line {

    private final Point start, end;
    private final double slope;
    private final boolean isVertical;

    private Color color;


    /**
     * Name: Geometry.Geometry.Line
     * Description: A constructor for Geometry.Geometry.Line.
     *
     * @param start the starting point of the line.
     * @param end   the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        if (Point.doubleEquals(start.getX(), end.getX())) {
            this.isVertical = true;
            this.slope = 0;
        } else {
            this.isVertical = false;
            this.slope = (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
        this.color = Color.BLACK;
    }

    /**
     * Name: Geometry.Geometry.Line
     * Description: A constructor for Geometry.Geometry.Line.
     *
     * @param x1 the x of the starting point
     * @param y1 the x of the starting point
     * @param x2 the y of the ending point
     * @param y2 the y of the ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Name: start
     * Description: getter for the starting point.
     *
     * @return the starting point.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Name: slope
     * Description: getter for our line's slope.
     *
     * @return the double value of our line's slope.
     */
    public double slope() {
        return this.slope;
    }

    /**
     * Name: isVertical
     * Description: getter for if our line is vertical.
     *
     * @return true if our line is vertical, and false if it's not.
     */
    public boolean isVertical() {
        return this.isVertical;
    }

    /**
     * Name: end
     * Description: getter for the ending point.
     *
     * @return the ending point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Name: length
     * Description: calculate our line length of our line.
     *
     * @return the double value of our line's length.
     */
    public double length() {
        /*
        the distance between our line's starting and ending point is our line's length
        */
        return this.start().distance(end());
    }

    /**
     * Name: middle
     * Description: calculate the middle point on our line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        return new Point((start().getX() + end().getX()) / 2, (start().getY() + end().getY()) / 2);
    }

    /**
     * Name: isIntersecting
     * Description: check if our line intersect with another line.
     *
     * @param other the other line we want to check if our line intersect with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // if the intersection point it's null(aka it exists) our lines intersects
        return intersectionPointWith(other) != null;
    }

    /**
     * Name: intersectionWith
     * Description: calculate the intersection point between our line and the given one.
     *
     * @param other the other line we want to find the intersection point with.
     * @return the intersection point if there is one(and only 1), otherwise it will return null.
     */
    public Point intersectionWith(Line other) {
        if (isMerging(other)) {
            return null;
        }
        return intersectionPointWith(other);
    }

    /**
     * Name: isOnSameSurface
     * Description: check if our line and the given line merge.
     *
     * @param other the other line we want to check if our line merge with.
     * @return true if the lines merge, false otherwise.
     */
    public boolean isMerging(Line other) {
        // if the 2 lines don't have the same slope, there is no way that they merge
        if (isVertical() != other.isVertical() || slope() != other.slope()) {
            return false;
        }
        /*
        if the 2 lines are vertical we need to check 2 things:
        1) if they have the same x value.
        2) if there is a point in between their Y values.
        */
        if (isVertical() && other.isVertical()) {
            return Point.doubleEquals(start().getX(), other.start().getX()) && pointInBetweenY(other) != null;
        }
        // magic number, every number will work
        double magicNum = 1;
        /*
        if our 2 lines we return the same y value from our line formula it means that
        they are on the same continues line, and if so, I need to check if there is
        a point in between their Y values.
        */
        if (Point.doubleEquals(slope() * (magicNum - start().getX()) + start().getY(),
                other.slope() * (magicNum - other.start().getX()) + other.start().getY())) {
            return pointInBetweenY(other) != null;
        }
        return false;
    }

    /**
     * Name: intersectionPointWith
     * Description: calculate the intersection point between our line and the given one.
     *
     * @param other the other line we want to find the intersection point with.
     * @return the intersection point if there is one (and if there are more
     * than 1 it will return 1 of them), and null if there is no intersection point.
     */
    private Point intersectionPointWith(Line other) {
        //if 1 or both of the lines are vertical
        if (isVertical() || other.isVertical()) {
            // if they are both vertical
            if (Point.doubleEquals(start().getX(), other.start().getX()) && isVertical() && other.isVertical()) {
                // return a valid point between the 2 lines' y.
                return pointInBetweenY(other);
            }
            // if only 1 of them is vertical
            if (isVertical()) {
                return oneIsVertical(other, this);
            }
            return oneIsVertical(this, other);
        }
        // if they have the same slope
        if (Point.doubleEquals(slope(), other.slope())) {
            // magic number, every number will work
            double magicNum = 1;
            //check if both lines are on the same continues line
            if (Point.doubleEquals(slope() * (magicNum - start().getX()) + start().getY(),
                    other.slope() * (magicNum - other.start().getX()) + other.start().getY())) {
                return pointInBetweenY(other);
            }
            return null;
        }
        // if none of them is vertical or have the same slope
        Point point = null;
        double x, y;
        // if our 2 lines were infinite, this x was where they would meet.
        x = (other.slope() * other.start().getX() - slope() * start().getX() + start().getY() - other.start().getY())
                / (other.slope() - slope());
        /*
        now I check of the x values of the start and end of the 2 lines in between
        the x value we calculate.
        */
        if (isInBetween(start().getX(), end().getX(), x) && isInBetween(other.start().getX(), other.end().getX(), x)) {
            // the y value of the intersection point
            y = slope() * (x - start().getX()) + start().getY();
            point = new Point(x, y);
        }
        return point;
    }

    /**
     * Name: equals
     * Description: check if 2 lines are equal to each other.
     *
     * @param other the line we want to check equality to.
     * @return return true if the 2 lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (start().equals(other.start()) && end().equals(other.end())) || (start().equals(other.end())
                && end().equals(other.start()));
    }

    /**
     * Name: isInBetween
     * Description: check if the target is between p1 and p2, doesn't matter the order.
     *
     * @param p1     the first point to check if target is between it.
     * @param p2     the second point to check if target is between it.
     * @param target the point we want to check if it between p1 and p2.
     * @return return true if the target is between p1 and p2 (doesn't matter the order), false otherwise.
     */
    private boolean isInBetween(double p1, double p2, double target) {
        //no point can be both me max and the min, so this is all works out
        return (Math.min(p1, p2) <= target) && (target <= Math.max(p1, p2)) || Point.doubleEquals(p1, target)
                || Point.doubleEquals(p2, target);
    }

    /**
     * Name: pointInBetweenY
     * Description: find a valid point on either line that is between the other's y values.
     *
     * @param other the other line we want to check.
     * @return return a valid point if there is one.
     */
    private Point pointInBetweenY(Line other) {
        double x, y;
        // the start of other between the start and end of this
        if (isInBetween(start().getY(), end().getY(), other.start().getY())) {
            x = other.start().getX();
            y = other.start().getY();
            return new Point(x, y);
        }
        // the end of other between the start and end of this
        if (isInBetween(start().getY(), end().getY(), other.end().getY())) {
            x = other.end().getX();
            y = other.end().getY();
            return new Point(x, y);
        }
        // the start of this between the start and end of other
        if (isInBetween(other.start().getY(), other.end().getY(), start().getY())) {
            x = start().getX();
            y = start().getY();
            return new Point(x, y);
        }
        // the end of this between the start and end of other
        if (isInBetween(other.start().getY(), other.end().getY(), end().getY())) {
            x = end().getX();
            y = end().getY();
            return new Point(x, y);
        }
        return null;
    }

    /**
     * Name: oneIsVertical
     * Description: find the intersection point between a not vertical line and a vertical one (if there is one).
     *
     * @param notVertical the not vertical line we want to check.
     * @param vertical    the vertical line we want to check.
     * @return return the intersection point between those 2 lines (if there is one).
     */
    private Point oneIsVertical(Line notVertical, Line vertical) {
        Point point = null;
        // if the 2 lines were infinite this could be value of their intersection point
        double x = vertical.start().getX();
        double y = notVertical.slope() * (x - notVertical.start().getX()) + notVertical.start().getY();
        // check if that protection intersection point is valid
        if (isInBetween(notVertical.start().getX(), notVertical.end().getX(), x) && isInBetween(vertical.start().getY(),
                vertical.end().getY(), y)) {
            point = new Point(x, y);
        }
        return point;
    }

    /**
     * Name: drawOn
     * Description: draw a line on the given draw surface.
     *
     * @param d the draw surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(getColor());
        d.drawLine(start().getIntX(), start().getIntY(), end().getIntX(), end().getIntY());
    }

    /**
     * Name: setColor
     * Description: setter for the line's color.
     *
     * @param color the color we want to set for the line.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Name: getColor
     * Description: getter for the line's color.
     *
     * @return the line's color.
     */
    public Color getColor() {
        return this.color;
    }


    /**
     * Name: closestIntersectionToStartOfLine
     * Description: find the closest intersection point to the start of the line if exists.
     *
     * @param rect the rectangle that we will check the closest intersection to the start of the line with.
     * @return return the closest intersection point to the start of the line if exists, otherwise return null.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        // if there are no intersection it means that the line doesn't intersect with the rectangle
        if (intersections.isEmpty()) {
            return null;
        }
        // if the intersection do exists, it means that at least 0 does exist.
        Point closestP = intersections.get(0);
        for (Point p : intersections) {
            if (start().distance(p) < start().distance(closestP)) {
                closestP = p;
            }
        }
        return closestP;
    }

    /**
     * Name: isPointOn
     * Description: check if the given point is on the line.
     *
     * @param point the point that we want to check if it is on the line.
     * @return Return true if the point is on the rectangle, otherwise returns false.
     */
    public boolean isPointOn(Point point) {
        // if the line isn't vertical
        if (!isVertical()) {
            double yIfPointIsOnLine = slope() * (point.getX() - start().getX()) + start().getY();
            // if the point is on the infinite line
            if (Point.doubleEquals(yIfPointIsOnLine, point.getY())) {
                return isInBetween(start().getX(), end().getX(), point.getX());
            }
            // if the line is vertical
        } else {
            if (Point.doubleEquals(point.getX(), start().getX())) {
                return isInBetween(start().getY(), end().getY(), point.getY());
            }
        }
        return false;
    }
}