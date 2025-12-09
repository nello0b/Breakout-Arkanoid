// 318844677 Netanel Berkovits


/**
 * Name: HitListener.
 * Description: HitListener interface.
 */
public interface HitListener {

    /**
     * Name: hitEvent
     * Description: This method is called whenever the beingHit object is hit.
     * The hitter parameter is the GameElements.Ball that's doing the hitting.
     *
     * @param beingHit the block that got hit.
     * @param hitter   the ball that hit it
     */
    void hitEvent(Block beingHit, Ball hitter);

    /**
     * Name: property
     * Description: Each HitListener.HitListener has a property, and it can return it.
     *
     * @return what the name of the property that we listen to
     */
    String property();

    /**
     * Name: addToBlock
     * Description: the black we want to add the listener to.
     *
     * @param b the black we want to add.
     */
    void addToBlock(Block b);
}
