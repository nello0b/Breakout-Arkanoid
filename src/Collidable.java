// 318844677 Netanel Berkovits


import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

/**
 * Name: Hit.Collidable interface.
 */
public interface Collidable {
    /**
     * Name: getCollisionRectangle
     * Description: Return the "collision shape" of the object.
     *
     * @return Return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Name: hit
     * Description: Notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  the collision point with the object.
     * @param currentVelocity the velocity of the colliding object.
     * @param hitter          the ball that hit this object.
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Name: addToGame
     * Description: Add the collidable object to the game.
     *
     * @param g the game that we want to add the collidable object to.
     */
    void addToGame(GameLevel g);

    /**
     * Name: getNotifiedForHit
     * Description: A getter for notifiedForHit.
     *
     * @return return the object's notifiedForHit
     */
    boolean getNotifiedForHit();
}
