// 318844677 Netanel Berkovits

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: Ball
 * Description: A Ball object.
 */
public class Ball implements Sprite {

    private static final int SPEED_CONSTANT = 53;
    private Point center;
    private final int radius;
    private java.awt.Color color;
    private Velocity velocity;

    private GameEnvironment environment;


    /**
     * Name: Ball
     * Description: A constructor for GameElements.Ball.
     *
     * @param center the center of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Name: Ball
     * Description: A constructor for GameElements.Ball.
     *
     * @param x     the x value of the center of the ball
     * @param y     the y value of the center of the ball
     * @param r     the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, Color color) {
        this(new Point(x, y), r, color);
    }

    /**
     * Name: setVelocity
     * Description: setter of velocity.
     *
     * @param v the velocity we want to set.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Name: getGameEnvironment
     * Description: A getter of gameEnvironment.
     *
     * @return the gameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return this.environment;
    }

    /**
     * Name: setGameEnvironment
     * Description: A setter for the ball's game environment.
     *
     * @param environment the paddle's game we want to set
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    /**
     * Name: setVelocityBySize
     * Description: set the velocity of the ball to be proportional to its size,
     * and with the given angle.
     *
     * @param angle the angle of the velocity we want to set.
     */
    public void setVelocityBySize(double angle) {
        setVelocity((Velocity.fromAngleAndSpeed(angle, Math.max(SPEED_CONSTANT - getSize(), 3))));
    }

    /**
     * Name: setVelocity
     * Description: setter of velocity.
     *
     * @param dx the x element of the velocity we want to set.
     * @param dy the y element of the velocity we want to set.
     */
    public void setVelocity(double dx, double dy) {
        setVelocity(new Velocity(dx, dy));
    }

    /**
     * Name: getVelocity
     * Description: getter for velocity.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Name: moveOneStep
     * Description: move the ball to its new location according to its velocity.
     */
    public void moveOneStep() {
        /*
        1) compute the ball trajectory (the trajectory is "how the ball will move
        without any obstacles" -- its a line starting at current location, and
        ending where the velocity will take the ball if no collisions will occur).
         */
        Line trajectory = getTrajectory();
        /*
        2) Check (using the game environment) if moving on this trajectory will hit anything.
         */
        CollisionInfo info = getGameEnvironment().getClosestCollision(trajectory);
        if (info != null) {
            /*
            2.2) Otherwise (there is a hit):
            2.2.2) move the ball to "almost" the hit point, but just slightly before it.
            */
            setCenter(almostHitPoint(info.collisionPoint(), 1));
            //2.2.3) notify the hit object (using its hit() method) that a collision occurred.
            //2.2.4) update the velocity to the new velocity returned by the hit() method.
            setVelocity(info.collisionObject().hit(this, info.collisionPoint(), getVelocity()));
            return;
        }
        // (to make sure the ball isn't stuck inside another Hit.Collidable)
        setCenter(almostHitPoint(getCenter(), 5));
        /*
         2.1) If no, then move the ball to the end of the trajectory.
         */
        setCenter(this.getVelocity().applyToPoint(getCenter()));
    }

    /**
     * Name: almostHitPoint
     * Description: the point the ball need to be in so it almost hit the Hit.Collidable.
     *
     * @param collisionPoint the collisionPoint of the ball with the Hit.Collidable
     * @param distance       the distance we want the ball to be from the wall of the Hit.Collidable.
     * @return the point that almost hit the Hit.Collidable
     */
    private Point almostHitPoint(Point collisionPoint, double distance) {
        // the point I want the ball to be in
        Point newP = new Point(collisionPoint.getX(), collisionPoint.getY());
        for (Collidable c : getGameEnvironment().getCollidables()) {
            Rectangle box = c.getCollisionRectangle();
            List<Line> sides = box.getSides();
            if (box.isPointOn(collisionPoint)) {
                List<Point> pointsToCheck = new ArrayList<Point>();
                // TOP - if the center of the ball would need be above the rectangle it will need to be there.
                pointsToCheck.add(new Point(newP.getX(), sides.get(0).start().getY() - getSize() * distance));
                /*
                RIGHT - if the center of the ball would need be to
                        the right of the rectangle it will need to be there.
                 */
                pointsToCheck.add(new Point(sides.get(1).start().getX() + getSize() * distance, newP.getY()));
                // BOTTOM - if the center of the ball would need be below the rectangle it will need to be there.
                pointsToCheck.add(new Point(newP.getX(), sides.get(2).start().getY() + getSize() * distance));
                // LEFT - if the center of the ball would need be to the left of the rectangle it will need to be there.
                pointsToCheck.add(new Point(sides.get(3).start().getX() - getSize() * distance, newP.getY()));
                // check if any of the pointsToCheck is inside another Hit.Collidable
                List<Point> potentialPoints = new ArrayList<Point>();
                for (Point p : pointsToCheck) {
                    boolean flag = true;
                    for (Collidable collidable : getGameEnvironment().getCollidables()) {
                        if (flag) {
                            flag = !collidable.getCollisionRectangle().isPointOn(p);
                        }
                    }
                    if (flag) {
                        potentialPoints.add(p);
                    }
                }
                Point closestPoint;
                // if there are no valid points.
                try {
                    closestPoint = potentialPoints.get(0);
                } catch (IndexOutOfBoundsException e) {
                    return collisionPoint;
                }
                for (Point p : potentialPoints) {
                    if (collisionPoint.distance(p) < collisionPoint.distance(closestPoint)) {
                        closestPoint = p;
                    }
                }
                if (newP.equals(collisionPoint)) {
                    newP = closestPoint;
                } else {
                    if (closestPoint.distance(newP) > closestPoint.distance(collisionPoint)) {
                        newP = closestPoint;
                    }
                }
            }
        }
        return newP;
    }

    /**
     * Name: getTrajectory
     * Description: create the ball's line trajectory.
     *
     * @return return the ball's trajectory
     */
    public Line getTrajectory() {
        return new Line(getCenter(), getVelocity().applyToPoint(this.center));
    }

    /**
     * Name: setCenter
     * Description: setter for the center of the ball.
     *
     * @param center the new center of the ball we want to set.
     */
    public void setCenter(Point center) {
        this.center = center;
    }


    /**
     * Name: getX
     * Description: getter for the x value of the ball.
     *
     * @return return the x value of the center of the ball
     */
    public int getX() {

        return this.center.getIntX();
    }

    /**
     * Name: getX
     * Description: getter for the ball's center.
     *
     * @return return the ball's center.
     */
    public Point getCenter() {

        return this.center;
    }

    /**
     * Name: getY
     * Description: getter for the y value of the ball.
     *
     * @return return the y value of the center of the ball
     */
    public int getY() {
        return this.center.getIntY();
    }

    /**
     * Name: setColor
     * Description: setter for the color of the ball.
     *
     * @param color the color we want to set for the ball
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Name: getSize
     * Description: getter for the ball's size(aka radius).
     *
     * @return return the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Name: getColor
     * Description: getter for the ball's color.
     *
     * @return return the ball's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Name: drawOn
     * Description: draw the ball on the draw serface.
     *
     * @param surface the DrawSurface element that we want to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle(getX(), getY(), getSize());
        surface.setColor(getColor());
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * Name: trajectory
     * Description: create the ball's line trajectory.
     *
     * @return the ball's line trajectory.
     */
    public Line trajectory() {
        return new Line(getCenter(), getVelocity().applyToPoint(getCenter()));
    }

    /**
     * Name: timePassed
     * Description: Move the ball a step.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Name: addToGame
     * Description: Add the ball to the game.
     *
     * @param gameLevel the game that we want to add the ball to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        setGameEnvironment(gameLevel.getEnvironment());
    }

}