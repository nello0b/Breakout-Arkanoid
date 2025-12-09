// 318844677 Netanel Berkovits
package Geometry;

/**
 * Name: Geometry.Geometry.Point Geometry.Geometry.Velocity
 * Description: Specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Name: Geometry.Geometry.Velocity
     * Description: A constructor for Geometry.Geometry.Velocity.
     *
     * @param dx the x part of the velocity.
     * @param dy the y part of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Name: Geometry.Geometry.Velocity
     * Description: A constructor for Geometry.Geometry.Velocity.
     *
     * @param v the velocity to base the new Geometry.Geometry.Velocity on.
     */
    public Velocity(Velocity v) {
        this(v.getDX(), v.getDY());
    }

    /**
     * Name: applyToPoint
     * Description: A constructor for Geometry.Geometry.Velocity.
     *
     * @param p the point that we want to apply the velocity to.
     * @return the point after the velocity have been applied to it.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + getDX(), p.getY() + getDY());
    }

    /**
     * Name: getDX
     * Description: Getter for our point's dx.
     *
     * @return the double value of our point's dx.
     */
    public double getDX() {
        return this.dx;
    }

    /**
     * Name: getDY
     * Description: Getter for our point's dy.
     *
     * @return the double value of our point's dy.
     */
    public double getDY() {
        return this.dy;
    }

    /**
     * Name: fromAngleAndSpeed
     * Description: Create constructor by using an angle and a speed.
     *
     * @param angle the angle of the velocity (in degrees).
     * @param speed the speed of the velocity
     * @return the velocity object with the given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin((((-angle + 180) * Math.PI)) / 180);
        double dy = speed * Math.cos((((angle + 180) * Math.PI)) / 180);
        return new Velocity(dx, dy);
    }

    /**
     * Name: fromAngleAndSpeed
     * Description: translate a trajectory to velocity.
     *
     * @param trajectory the trajectory of the object as a line.
     * @return the velocity of the trajectory.
     */
    public static Velocity lineToVelocity(Line trajectory) {
        return new Velocity(trajectory.end().getX() - trajectory.start().getX(),
                trajectory.end().getY() - trajectory.start().getY());
    }

    /**
     * Name: getSpeed
     * Description: calculate the speed of the velocity.
     *
     * @return return the speed of the velocity.
     */
    public double getSpeed() {
        Point origin = new Point(0, 0);
        Point end = applyToPoint(origin);
        Line trajectory = new Line(origin, end);
        return trajectory.length();
    }
}