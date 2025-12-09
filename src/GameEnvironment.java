// 318844677 Netanel Berkovits


import Geometry.Line;
import Geometry.Point;
import Geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Hit.GameEnvironment Class
 * Description: A GameElements.Game Environment object that manage Hit.Collidable object.
 */
public class GameEnvironment {

    private final List<Collidable> collidables;

    /**
     * Name: Hit.GameEnvironment
     * Description: A constructor for Hit.GameEnvironment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Name: getCollidables
     * Description: Getter for the Collidables list.
     *
     * @return the Collidables list.
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * Name: addCollidable
     * Description: Add the given collidable to the environment.
     *
     * @param c the Hit.Collidable object that we add to the list.
     */
    public void addCollidable(Collidable c) {
        getCollidables().add(c);
    }

    /**
     * Name: removeCollidable
     * Description: Remove to the given collidable to the environment.
     *
     * @param c the Hit.Collidable object that we remove to the list.
     */
    public void removeCollidable(Collidable c) {
        getCollidables().remove(c);
    }

    /**
     * Name: getClosestCollision
     * Description: Assume an object moving from line.start() to line.end(). If this object
     * will not collide with any of the collidables in this collection, return null.
     * Else, return the information about the closest collision that is going to occur.
     *
     * @param trajectory the trajectory of the moving object.
     * @return return the information about the closest collision it exists, otherwise return null.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // closest Collision point and the closest Hit.Collidable object.
        Point closestCollision = null;
        Collidable closestCollidable = null;
        // the bounds of the object
        Rectangle rect;
        // the collision point
        Point collisionP;
        for (Collidable c : getCollidables()) {
            rect = c.getCollisionRectangle();
            collisionP = trajectory.closestIntersectionToStartOfLine(rect);
            if (collisionP != null) {
                // if there is no closestCollision point this one is the closest
                if (closestCollision == null) {
                    closestCollision = collisionP;
                    closestCollidable = c;
                } else {
                    // check if the new collisionP is closest to the start than closestCollision.
                    if (trajectory.start().distance(collisionP) < trajectory.start().distance(closestCollision)) {
                        closestCollision = collisionP;
                        closestCollidable = c;
                    }
                }
            }
        }
        if (closestCollidable != null) {
            return new CollisionInfo(closestCollidable, closestCollision);
        }
        return null;
    }
}
