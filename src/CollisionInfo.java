// 318844677 Netanel Berkovits


import Geometry.Point;

/**
 * Name: Hit.CollisionInfo Class
 * Description: A Collision Info object that gives information about a collision.
 */
public class CollisionInfo {

    private final Point collisionPoint;
    private final Collidable collisionObject;

    /**
     * Name: Hit.CollisionInfo
     * Description: A constructor for Hit.CollisionInfo.
     *
     * @param obj the collision object.
     * @param p   the collision point
     */
    public CollisionInfo(Collidable obj, Point p) {
        this.collisionPoint = p;
        this.collisionObject = obj;
    }

    /**
     * Name: collisionPoint
     * Description: A getter for collisionPoint.
     *
     * @return return collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * Name: collisionObject
     * Description: A getter for collisionObject.
     *
     * @return return collisionObject.
     */
    public Collidable collisionObject() {
        return this.collisionObject;

    }
}
