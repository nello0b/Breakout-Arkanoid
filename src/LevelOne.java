// 318844677 Netanel Berkovits


import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: LevelOne.
 * Description: LevelOne Class.
 */
public class LevelOne implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 1;
    private static final String LEVEL_NAME = "Once Shot = Once Kill";
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 1;

    public static final int BLOCK_SIZE = Configuration.BOARDER_SIZE * 2;

    public static final Point BLOCK_TOP_LEFT =
            new Point((double) (Configuration.SCREEN_WIDTH - BLOCK_SIZE) / 2,
                    (double) Configuration.SCREEN_HIGHT / 5);

    /**
     * Name: numberOfBalls
     * Description: The number of balls in the level.
     *
     * @return the number of balls in the level.
     */
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * Name: initialBallVelocities
     * Description: The initial velocity of each ball,
     * note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return return the velocity of each ball.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocitys = new ArrayList<Velocity>();
        velocitys.add(Velocity.fromAngleAndSpeed(0, Configuration.DEFAULT_SPEED));
        return velocitys;
    }

    /**
     * Name: paddleSpeed
     * Description: The speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return Configuration.DEFAULT_SPEED;
    }

    /**
     * Name: paddleWidth
     * Description: The width of the paddle.
     *
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return Configuration.DEFAULT_PADDLE_WIDTH;
    }

    /**
     * Name: levelName
     * Description: The name of the level.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return LEVEL_NAME;
    }


    /**
     * Name: getBackground
     * Description: The background of the level.
     *
     * @return the background of the level.
     */
    public Sprite getBackground() {
        return new LevelOneBackground();
    }


    /**
     * Name: blocks
     * Description: The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        // That one block you need to hit
        Block thatOneBlockYouNeedToHit =
                new Block(new Rectangle(BLOCK_TOP_LEFT, BLOCK_SIZE,
                        BLOCK_SIZE));
        thatOneBlockYouNeedToHit.setProperty("Destructible", true);
        thatOneBlockYouNeedToHit.setProperty("Scorable", true);
        thatOneBlockYouNeedToHit.setColor(Color.RED);
        blocks.add(thatOneBlockYouNeedToHit);

        return blocks;
    }

    /**
     * Name: numberOfBlocksToRemove
     * Description: The Number of blocks that should be removed before the level is considered to be "cleared"
     * This number should be <= blocks.size();.
     *
     * @return the Number of blocks that should be removed.
     */
    public int numberOfBlocksToRemove() {
        return blocks().size();
    }

    /**
     * Name: puddleSize
     * Description: The size of the puddle.
     *
     * @return the size of the puddle.
     */
    public int puddleSize() {
        return Configuration.DEFAULT_PADDLE_WIDTH;
    }
}
