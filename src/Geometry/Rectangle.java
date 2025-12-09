// 318844677 Netanel Berkovits
package Geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Box
 * Description: A Geometry.Geometry.Rectangle object.
 */
public class Rectangle {

    private final Point upperLeft;
    private final double width;
    private final double height;
    private java.awt.Color color;

    /**
     * Name: Box
     * Description: A constructor for Geometry.Geometry.Rectangle.
     *
     * @param p      the top left corner of the rectangle.
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(Point p, double width, double height) {
        this.upperLeft = p;
        this.width = width;
        this.height = height;
    }

    /**
     * Name: Box
     * Description: A constructor for Geometry.Geometry.Rectangle.
     *
     * @param x      the x value of the top left corner of the rectangle.
     * @param y      the y value of the top left corner of the rectangle.
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);

    }

    /**
     * Name: Box
     * Description: A constructor for Geometry.Geometry.Rectangle.
     *
     * @param upperLeft   the top left corner of the rectangle.
     * @param bottomRight the bottom right corner of the rectangle.
     */
    public Rectangle(Point upperLeft, Point bottomRight) {
        this(upperLeft, bottomRight.getIntX() - upperLeft.getIntX(), bottomRight.getIntY() - upperLeft.getIntY());

    }

    /**
     * Name: getBottomRight
     * Description: calculate the bottom right corner of the point.
     *
     * @return the Bottom Right corner of the rectangle.
     */
    public Point getBottomRight() {
        return new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
    }

    /**
     * Name: getWidth
     * Description: getter for the rectangle's width.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Name: getHeight
     * Description: getter for the rectangle's height.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Name: getTopLeft
     * Description: getter for the rectangle's top left corner.
     *
     * @return the top left corner of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Name: getCorners
     * Description: calculate the 4 corners of the rectangle.
     *
     * @return Return an ArrayList of the corners of the rectangle.
     */
    public List<Point> getCorners() {
        List<Point> list = new ArrayList<Point>();
        //Upper Left 0
        list.add(getUpperLeft());
        //Upper Right 1
        list.add(new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY()));
        //Bottom Right 2
        list.add(getBottomRight());
        //Bottom Left 3
        list.add(new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight()));
        return list;
    }

    /**
     * Name: getSides
     * Description: calculate the 4 sides of the rectangle.
     *
     * @return Return an ArrayList of the sides of the rectangle.
     */
    public List<Line> getSides() {
        List<Line> list = new ArrayList<Line>();
        List<Point> corners = getCorners();
        //Up 0
        list.add(new Line(corners.get(0), corners.get(1)));
        //Right 1
        list.add(new Line(corners.get(1), corners.get(2)));
        //Bottom 2
        list.add(new Line(corners.get(2), corners.get(3)));
        //Left 3
        list.add(new Line(corners.get(3), corners.get(0)));
        return list;
    }

    /**
     * Name: intersectionPoints
     * Description: calculate the intersection points with the given line, if they exist.
     *
     * @param line the line that we will check intersection with.
     * @return Return a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        List<Line> sides = getSides();
        List<Point> list = new ArrayList<Point>();
        for (Line side : sides) {
            // check if an intersection point with the sides of the rectangle exists
            if (side.isIntersecting(line) && !side.isMerging(line)) {
                list.add(side.intersectionWith(line));
            }
        }
        return list;
    }

    /**
     * Name: isPointOn
     * Description: check if the given point is on the rectangle.
     *
     * @param point the point that we want to check if it is on the rectangle.
     * @return Return true if the point is on the rectangle, otherwise returns false.
     */
    public boolean isPointOn(Point point) {
        // check X
        boolean betweenX = Point.doubleGreaterEqual(point.getX(), getUpperLeft().getX()) && Point.doubleGreaterEqual(
                getBottomRight().getX(), point.getX());
        // check Y
        boolean betweenY = Point.doubleGreaterEqual(point.getY(), getUpperLeft().getY()) && Point.doubleGreaterEqual(
                getBottomRight().getY(), point.getY());
        return betweenX && betweenY;
    }

    /**
     * Name: drawOn
     * Description: draw the ball on the draw serface.
     *
     * @param surface      the DrawSurface element that we want to draw on.
     * @param outlineColor the outline color of the rectangle.
     * @param insideColor  the inside color of the rectangle
     */
    public void drawOn(DrawSurface surface, Color outlineColor, Color insideColor) {
        surface.setColor(insideColor);
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
        surface.setColor(outlineColor);
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(),
                (int) getWidth(), (int) getHeight());
    }
}
