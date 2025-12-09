// 318844677 Netanel Berkovits

import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Name: GameElements.Block Class
 * Description: A GameElements.Block object that implements Hit.Collidable and Sprite.Sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;

    private final List<HitListener> hitListeners;

    private Color color;
    private boolean notifiedForHit;
    private GameEnvironment environment;

    private final Map<String, Boolean> properties;

    private Color boarder;

    /**
     * Name: Block
     * Description: A constructor for GameElements.Block.
     *
     * @param rect the rectangle that represents the block.
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
        this.notifiedForHit = false;
        this.color = Color.BLACK;
        this.hitListeners = new ArrayList<HitListener>();
        this.properties = new HashMap<String, Boolean>();
        resetProperties();
        boarder = Color.BLACK;
    }

    /**
     * Name: resetProperties
     * Description: reset the properties of the ball.
     */
    public void resetProperties() {
        this.properties.clear();
        this.properties.put("Destructible", false);
        this.properties.put("Ball_Killer", false);
        this.properties.put("Scorable", false);
    }

    /**
     * Name: getProperties
     * Description: A getter for the block's properties.
     *
     * @return return the map of the block's properties.
     */
    public Map<String, Boolean> getProperties() {
        return new HashMap<String, Boolean>(this.properties);
    }

    /**
     * Name: setProperty
     * Description: Set a property value.
     *
     * @param property the name of the property.
     * @param value    the value of the property.
     */
    public void setBoarder(String property, Boolean value) {
        if (this.properties.containsKey(property)) {
            this.properties.put(property, value);
        }
    }

    /**
     * Name: setProperty
     * Description: Set a property value.
     *
     * @param property the name of the property.
     * @param value    the value of the property.
     */
    public void setProperty(String property, Boolean value) {
        if (this.properties.containsKey(property)) {
            this.properties.put(property, value);
        }
    }

    /**
     * Name: Block
     * Description: A constructor for Block.
     *
     * @param x      the x value of the top left corner of the block.
     * @param y      the y value of the top left corner of the block.
     * @param width  the width of the block.
     * @param height the height of the block.
     */
    public Block(double x, double y, double width, double height) {
        this(new Rectangle(x, y, width, height));
    }

    /**
     * Name: Block
     * Description: A constructor for GameElements.Block.
     *
     * @param x      the x value of the top left corner of the block.
     * @param y      the y value of the top left corner of the block.
     * @param width  the width of the block.
     * @param height the height of the block.
     * @param color  the color of the block.
     */
    public Block(double x, double y, double width, double height, Color color) {
        this(new Rectangle(x, y, width, height));
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
     * @return return the block's notifiedForHit
     */
    public boolean getNotifiedForHit() {
        return this.notifiedForHit;
    }


    /**
     * Name: getRectangle
     * Description: getter for the block's rectangle.
     *
     * @return return the block's rectangle.
     */
    private Rectangle getRectangle() {
        return this.rectangle;
    }

    /**
     * Name: getCollisionRectangle
     * Description: getter for the block's rectangle.
     *
     * @return return the block's rectangle.
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
        boolean isHit = getCollisionRectangle().isPointOn(collisionPoint);
        double newDx = currentVelocity.getDX();
        double newDy = currentVelocity.getDY();
        List<Line> sides = getCollisionRectangle().getSides();
        if (isHit) {
            // notify all for hit!
            notifyHit(hitter);
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
     * Name: drawOn
     * Description: draw the ball on the draw serface.
     *
     * @param surface the DrawSurface element that we want to draw on.
     */
    public void drawOn(DrawSurface surface) {
        getRectangle().drawOn(surface, getBoarder(), getColor());
    }

    /**
     * Name: setBoarder
     * Description: setter for the boarder color of the block.
     *
     * @param color the boarder color we want to set for the block
     */
    public void setBoarder(Color color) {
        this.boarder = color;
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
     * Name: getBoarder
     * Description: getter for the block's boarder color.
     *
     * @return return the block's boarder color.
     */
    public Color getBoarder() {
        return this.boarder;
    }

    /**
     * Name: timePassed
     * Description: implementing sprite method, does nothing at the moment.
     */
    public void timePassed() {
        setColor(getColor());
    }

    /**
     * Name: addToGame
     * Description: Add the block to the game.
     *
     * @param gameLevel the game that we want to add the block to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
        setGameEnvironment(gameLevel.getEnvironment());
    }

    /**
     * Name: removeFromGame
     * Description: Remove the block to the game.
     *
     * @param gameLevel the game that we want to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * Name: getHitListeners
     * Description: A getter for the hitListeners list.
     *
     * @return return the hitListeners list.
     */
    private List<HitListener> getHitListeners() {
        return this.hitListeners;
    }

    /**
     * Name: notifyHit
     * Description: Notify all the objects in the hitListeners list.
     *
     * @param hitter the ball that triggered the hit.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(getHitListeners());
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Name: addHitListener
     * Description: Add hl as a listener to hit events.
     *
     * @param hl the HitListener.HitListener we want to add to listen to hit events.
     */
    public void addHitListener(HitListener hl) {
        getHitListeners().add(hl);
    }

    /**
     * Name: removeHitListener
     * Description: Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener.HitListener we want to remove from listening to hit events.
     */
    public void removeHitListener(HitListener hl) {
        getHitListeners().remove(hl);
    }
}
