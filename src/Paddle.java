// 318844677 Netanel Berkovits

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * Name: GameElements.Paddle Class
 * Description: A GameElements.Paddle object.
 */
public class Paddle implements Collidable, Sprite {

    private final KeyboardSensor keyboard;

    private Rectangle rectangle;

    private char bounceMode;

    private Color color;

    private boolean notifiedForHit;

    private double speed;

    private GameEnvironment environment;

    /**
     * Name: Paddle
     * Description: A constructor for Paddle.
     *
     * @param rect     the rectangle that represents the paddle.
     * @param keyboard the keyboard input.
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.rectangle = rect;
        this.notifiedForHit = false;
        this.color = Color.BLACK;
        setBounceMode('N');
        this.speed = 5;
    }

    /**
     * Name: Paddle
     * Description: A constructor for Paddle.
     *
     * @param rect     the rectangle that represents the paddle.
     * @param keyboard the keyboard input.
     * @param color    the color of the paddle.
     */
    public Paddle(Rectangle rect, KeyboardSensor keyboard, Color color) {
        this(rect, keyboard);
        setColor(color);
    }

    /**
     * Name: GameElements.Paddle
     * Description: A constructor for GameElements.Paddle.
     *
     * @param x        the x value of the top left corner of the paddle.
     * @param y        the y value of the top left corner of the paddle.
     * @param width    the width of the paddle.
     * @param height   the height of the paddle.
     * @param keyboard the keyboard input.
     */
    public Paddle(double x, double y, double width, double height, KeyboardSensor keyboard) {
        this(new Rectangle(x, y, width, height), keyboard);
    }

    /**
     * Name: GameElements.Paddle
     * Description: A constructor for GameElements.Paddle.
     *
     * @param x        the x value of the top left corner of the paddle.
     * @param y        the y value of the top left corner of the paddle.
     * @param width    the width of the paddle.
     * @param height   the height of the paddle.
     * @param keyboard the keyboard input.
     * @param color    the color of the paddle.
     */
    public Paddle(double x, double y, double width, double height, KeyboardSensor keyboard, Color color) {
        this(new Rectangle(x, y, width, height), keyboard);
        setColor(color);
    }


    /**
     * Name: setGameEnvironment
     * Description: A setter for the puddle's game environment.
     *
     * @param environment the paddle's game we want to set
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.environment = environment;
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
     * Name: getSpeed
     * Description: A gettter for the puddle's speed.
     *
     * @return the paddle's speed
     */
    public double getSpeed() {
        return this.speed;
    }

    /**
     * Name: setSpeed
     * Description: A setter for the puddle's speed.
     *
     * @param s the paddle's speed we want to set
     */
    public void setSpeed(double s) {
        this.speed = s;
    }

    /**
     * Name: setBounceMode
     * Description: A setter for the puddle's bounceMode.
     *
     * @param mode the bounce mode.
     */
    public void setBounceMode(char mode) {
        switch (mode) {
            case 'F':
            case 'N':
                this.bounceMode = mode;
                break;
            default:
                String massage = "The BounceMode you entered is not valid, "
                        + "so it is set to 'N' - Normal Bounce GameElements.Ball";
                System.out.println(massage);
                this.bounceMode = 'N';
        }
    }

    /**
     * Name: getBounceMode
     * Description: A getter for the paddle's bounceMode.
     *
     * @return return the paddle's bounceMode.
     */
    public char getBounceMode() {
        return this.bounceMode;
    }


    /**
     * Name: setColor
     * Description: setter for the color of the block.
     *
     * @param color the color we want to set for the block
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Name: getColor
     * Description: getter for the block's color.
     *
     * @return return the block's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Name: setRectangle
     * Description: A setter for the paddle's rectangle.
     *
     * @param rect the rectangle we want to set for the paddle.
     */
    private void setRectangle(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * Name: moveLeft
     * Description: mover the paddle to the left.
     */
    public void moveLeft() {
        double s = getSpeed();
        Rectangle r = getRectangle();
        Rectangle newR =
                new Rectangle(r.getUpperLeft().getX() - s, r.getUpperLeft().getY(), r.getWidth(), r.getHeight());
        Line topOfNewR = newR.getSides().get(0);
        for (Collidable c : getGameEnvironment().getCollidables()) {
            List<Line> sides = c.getCollisionRectangle().getSides();
            if (sides.get(1).isIntersecting(topOfNewR)) {
                s = r.getUpperLeft().getX() - sides.get(1).intersectionWith(topOfNewR).getX();
            }
        }
        movePaddle(s * -1);
    }

    /**
     * Name: movePaddle
     * Description: move the paddle with the relevant speed.
     *
     * @param speed the speed we want to move the paddle by.
     */
    public void movePaddle(double speed) {
        Rectangle r = getRectangle();
        setRectangle(
                new Rectangle(r.getUpperLeft().getX() + speed, r.getUpperLeft().getY(), r.getWidth(), r.getHeight()));
    }

    /**
     * Name: moveRight
     * Description: mover the paddle to the right.
     */
    public void moveRight() {
        double s = getSpeed();
        Rectangle r = getRectangle();
        Rectangle newR =
                new Rectangle(r.getUpperLeft().getX() + s, r.getUpperLeft().getY(), r.getWidth(), r.getHeight());
        Line topOfNewR = newR.getSides().get(0);
        for (Collidable c : getGameEnvironment().getCollidables()) {
            List<Line> sides = c.getCollisionRectangle().getSides();
            if (sides.get(3).isIntersecting(topOfNewR)) {
                s = sides.get(3).intersectionWith(topOfNewR).getX() - r.getBottomRight().getX();
            }
        }
        movePaddle(s);
    }

    /**
     * Name: getRectangle
     * Description: getter for the paddle's rectangle.
     *
     * @return return the paddle's rectangle.
     */
    private Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * Name: setNotifiedForHit
     * Description: setter for notifiedForHit.
     *
     * @param isHit does the object is hit.
     */
    private void setNotifiedForHit(boolean isHit) {
        this.notifiedForHit = isHit;
    }

    /**
     * Name: getNotifiedForHit
     * Description: A getter for notifiedForHit.
     *
     * @return return the paddle's notifiedForHit
     */
    public boolean getNotifiedForHit() {
        return this.notifiedForHit;
    }

    // Sprite.Sprite

    /**
     * Name: timePassed
     * Description: get an input and mover the paddle according to the input.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Name: drawOn
     * Description: draw the paddle on the draw surface.
     *
     * @param surface the draw surface we want to draw on.
     */
    public void drawOn(DrawSurface surface) {
        getRectangle().drawOn(surface, Color.BLACK, getColor());
    }

    // Hit.Collidable

    /**
     * Name: getCollisionRectangle
     * Description: getter for the block's rectangle.
     *
     * @return return the collision rectangle of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return getRectangle();
    }

    /**
     * Name: hit
     * Description: Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the collision point with the object.
     * @param currentVelocity the velocity of the colliding object.
     * @param hitter          the ball that hit this object.
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        setNotifiedForHit(getCollisionRectangle().isPointOn(collisionPoint));
        Velocity newV = new Velocity(currentVelocity);
        if (getNotifiedForHit()) {
            switch (getBounceMode()) {
                case 'F':
                    newV = funBounce(collisionPoint, currentVelocity);
                    break;
                case 'N':
                    newV = normalBounce(collisionPoint, currentVelocity);
                    break;
                default:
                    System.out.println("The GameElements.Paddle Bounce mode is invalid!");
            }
        }
        return newV;
    }

    private Velocity funBounce(Point collisionPoint, Velocity currentVelocity) {
        List<Line> sides = getCollisionRectangle().getSides();
        List<Line> funSections = new ArrayList<Line>();
        double length = sides.get(0).length();
        // 1
        funSections.add(new Line(sides.get(0).start(),
                new Point(sides.get(0).start().getX() + (length / 5), sides.get(0).start().getY())));
        // 2
        funSections.add(new Line(funSections.get(0).end(),
                new Point(funSections.get(0).end().getX() + (length / 5), funSections.get(0).start().getY())));
        // 3
        funSections.add(new Line(funSections.get(1).end(),
                new Point(funSections.get(1).end().getX() + (length / 5), funSections.get(1).start().getY())));
        // 4
        funSections.add(new Line(funSections.get(2).end(),
                new Point(funSections.get(2).end().getX() + (length / 5), funSections.get(2).start().getY())));
        // 5
        funSections.add(new Line(funSections.get(3).end(), sides.get(0).end()));
        // new Geometry.Geometry.Velocity
        Velocity newV = new Velocity(currentVelocity);
        if (getNotifiedForHit()) {
            // upper bound
            if (sides.get(0).isPointOn(collisionPoint)) {
                if (funSections.get(2).isPointOn(collisionPoint)) {
                    // 3
                    newV = new Velocity(currentVelocity.getDX(), currentVelocity.getDY() * -1);
                } else if (funSections.get(0).isPointOn(collisionPoint)) {
                    // 1
                    newV = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                } else if (funSections.get(4).isPointOn(collisionPoint)) {
                    // 5
                    newV = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                } else if (funSections.get(1).isPointOn(collisionPoint)) {
                    // 2
                    newV = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                } else {
                    // 4
                    newV = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                }
            } else if (sides.get(1).isPointOn(collisionPoint)) {
                // left bound
                newV = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY() * -1);
            } else if (sides.get(2).isPointOn(collisionPoint)) {
                // bottom boundnd
                newV = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY());
            } else if (sides.get(3).isPointOn(collisionPoint)) {
                // right bound
                newV = new Velocity(currentVelocity.getDX() * -1, currentVelocity.getDY() * -1);
            }


        }
        return newV;
    }

    private Velocity normalBounce(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDX();
        double newDy = currentVelocity.getDY();
        List<Line> sides = getCollisionRectangle().getSides();
        if (getNotifiedForHit()) {
            // upper bound
            if (sides.get(0).isPointOn(collisionPoint)) {
                newDy *= -1;
            }
            // left bound
            if (sides.get(1).isPointOn(collisionPoint)) {
                newDx *= -1;
            }
            // bottom bound
            if (sides.get(2).isPointOn(collisionPoint)) {
                newDy *= -1;
            }
            // right bound
            if (sides.get(3).isPointOn(collisionPoint)) {
                newDx *= -1;
            }
        }
        return new Velocity(newDx, newDy);
    }

    /**
     * Name: addToGame
     * Description: add the paddle to the game.
     *
     * @param gameLevel the game that we want to add the paddle to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
        setGameEnvironment(gameLevel.getEnvironment());
    }

}
