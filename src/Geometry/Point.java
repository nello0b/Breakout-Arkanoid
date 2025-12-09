// 318844677 Netanel Berkovits
package Geometry;

/**
 * Name: Geometry.Geometry.Point Class
 * Description: A point object that represent a point by an X and Y values.
 */
public class Point {
    private final double x;
    private final double y;

    private static final double COMPARISON_THRESHOLD = 0.00001;

    /**
     * Name: Geometry.Geometry.Point
     * Description: A constructor for Geometry.Geometry.Point.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // distance -- return the distance of this point to the other point

    /**
     * Name: distance
     * Description: calculate the distance between 2 points.
     *
     * @param other the point that we are calculating the distance from.
     * @return the double value of the distance.
     */
    public double distance(Point other) {
        // A simple distance formula
        double x = getX() - other.getX();
        double y = getY() - other.getY();
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Name: equals
     * Description: check if our point is equal to the given point.
     *
     * @param other the point that we want to check if our point is equal to.
     * @return return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return doubleEquals(other.getX(), getX()) && doubleEquals(other.getY(), getY());
    }

    // Return the x and y values of this point

    /**
     * Name: getX
     * Description: getter for our point's x.
     *
     * @return the double value of our point's x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Name: getY
     * Description: getter for our point's y.
     *
     * @return the double value of our point's y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Name: getIntX
     * Description: getter for our point's x.
     *
     * @return the Int value of our point's x, and it round it to the nearest half.
     */
    public int getIntX() {
        return roundToHalf(getX());
    }

    /**
     * Name: getIntY
     * Description: getter for our point's y.
     *
     * @return the Int value of our point's y, and it round it to the nearest half.
     */
    public int getIntY() {
        return roundToHalf(getY());
    }

    /**
     * Name: roundToHalf
     * Description: round a double number to its nearest half int value.
     *
     * @param n the double number we want to round.
     * @return the Int value of our point's y, and it round it to the nearest half.
     */
    private int roundToHalf(double n) {
        return (int) (Math.round(n * 2) / 2.0);
    }

    /**
     * Name: doubleEquals
     * Description: check if a equal to b with the comparison threshold.
     *
     * @param a the first number.
     * @param b the second number.
     * @return if a equal to b with the comparison threshold.
     */
    public static boolean doubleEquals(double a, double b) {
        return Math.abs(a - b) < Point.COMPARISON_THRESHOLD;
    }

    /**
     * Name: doubleGreaterEqual
     * Description: check if a greater or equal to b with the comparison threshold (>=).
     *
     * @param a the first number.
     * @param b the second number.
     * @return if a equal to b with the comparison threshold.
     */
    public static boolean doubleGreaterEqual(double a, double b) {
        return (Math.abs(a - b) < Point.COMPARISON_THRESHOLD) || a >= b;
    }
}