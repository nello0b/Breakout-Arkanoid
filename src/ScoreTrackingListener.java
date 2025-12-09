// 318844677 Netanel Berkovits


/**
 * Name: HitListener.ScoreTrackingListener
 * Description: A HitListener.ScoreTrackingListener Object.
 */
public class ScoreTrackingListener implements HitListener {

    private static final String PROPERTY = "Scorable";
    private final Counter currentScore;

    private static final int SCORE_INCREMENT = 5;

    /**
     * Name: HitListener.ScoreTrackingListener
     * Description: A constructor for HitListener.ScoreTrackingListener.
     *
     * @param scoreCounter the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Name: property
     * Description: Each HitListener.HitListener has a property, and it can return it.
     *
     * @return what the name of the property that we listen to
     */
    public String property() {
        return PROPERTY;
    }

    /**
     * Name: currentScore
     * Description: A getter for the currentScore counter.
     *
     * @return the currentScore counter.
     */
    public Counter currentScore() {
        return this.currentScore;
    }

    /**
     * Name: hitEvent
     * Description: Incrementing the score.
     *
     * @param beingHit the block that got hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore().increase(SCORE_INCREMENT);
    }

    /**
     * Name: addToBlock
     * Description: the black we want to add the listener to.
     *
     * @param b the black we want to add.
     */
    public void addToBlock(Block b) {
        if (b.getProperties().get(PROPERTY)) {
            b.addHitListener(this);
        }
    }
}