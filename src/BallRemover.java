// 318844677 Netanel Berkovits


/**
 * Name: HitListener.BallRemover
 * Description: A HitListener.BallRemover is in charge of removing balls
 * from the game, as well as keeping count of the number of balls that remain.
 */
public class BallRemover implements HitListener {

    private static final String PROPERTY = "Ball_Killer";
    private final GameLevel gameLevel;
    private final Counter remainingBalls;

    /**
     * Name: HitListener.BallRemover
     * Description: A constructor for HitListener.BallRemover.
     *
     * @param gameLevel      the game that we want to remove balls from.
     * @param remainingBalls the number of balls in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
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
     * Name: getGame
     * Description: getter for game.
     *
     * @return the game of the HitListener.BlockRemover.
     */
    public GameLevel getGame() {
        return this.gameLevel;
    }

    /**
     * Name: remainingBalls
     * Description: A getter for remainingBlocks counter.
     *
     * @return the remainingBlocks counter.
     */
    public Counter remainingBalls() {
        return this.remainingBalls;
    }

    /**
     * Name: hitEvent
     * Description: Balls that got hit are removed.
     *
     * @param beingHit the block that got hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        getGame().removeSprite(hitter);
        remainingBalls().decrease(1);
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