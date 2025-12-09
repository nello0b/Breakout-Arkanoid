// 318844677 Netanel Berkovits


/**
 * Name: HitListener.BlockRemover
 * Description: A HitListener.BlockRemover is in charge of removing blocks
 * from the game, as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {

    private static final String PROPERTY = "Destructible";
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Name: HitListener.BlockRemover
     * Description: A constructor for HitListener.BlockRemover.
     *
     * @param gameLevel       the game that we want to remove blocks from.
     * @param remainingBlocks the number of blocks in the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
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
     * Name: remainingBlocks
     * Description: A getter for remainingBlocks counter.
     *
     * @return the remainingBlocks counter.
     */
    public Counter remainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * Name: hitEvent
     * Description: Blocks that are hit should be removed from the game.
     * Remember to remove this listener from the block that is being removed from the game.
     *
     * @param beingHit the block that got hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        getGame().removeCollidable(beingHit);
        getGame().removeSprite(beingHit);
        remainingBlocks().decrease(1);
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
            remainingBlocks().increase(1);
        }
    }
}