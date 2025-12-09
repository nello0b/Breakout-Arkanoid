// 318844677 Netanel Berkovits


import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: LevelTwo.
 * Description: LevelTwo Class.
 */
public class LevelTwo implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 7;
    private static final String LEVEL_NAME = "Here Come the Sun";
    private static final int NUMBER_OF_BLOCKS_TO_REMOVE = 20;

    private static final int BLOCK_WIDTH = (int) Math.round(
            (double) (Configuration.SCREEN_WIDTH - (Configuration.BOARDER_SIZE * 2)) / NUMBER_OF_BLOCKS_TO_REMOVE);
    public static final Point BLOCK_TOP_LEFT = new Point(Configuration.BOARDER_SIZE, Configuration.BOARDER_SIZE * 12);

    public static final Point SUN_CENTER = new Point(Configuration.BOARDER_SIZE + BLOCK_WIDTH * 3,
            (double) (BLOCK_TOP_LEFT.getIntY() + Configuration.BOARDER_SIZE * 3) / 2);

    public static final int SUN_RADIUS = 60;

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
        return new LevelTwoBackground();
    }


    /**
     * Name: blocks
     * Description: The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        Color[] rainbow =
                {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(177, 0, 255), Color.MAGENTA};

        List<Block> blocks = new ArrayList<Block>();
        // Parameters
        int blockHeight = Configuration.BOARDER_SIZE;
        int x = BLOCK_TOP_LEFT.getIntX();
        int y = BLOCK_TOP_LEFT.getIntY();
        Block b;
        for (int i = 0; i < NUMBER_OF_BLOCKS_TO_REMOVE; i++) {
            b = new Block(new Rectangle(x + (i * BLOCK_WIDTH), y, BLOCK_WIDTH, blockHeight));
            b.setProperty("Destructible", true);
            b.setProperty("Scorable", true);
            b.setColor(rainbow[(int) Math.floor((double) i / ((double) NUMBER_OF_BLOCKS_TO_REMOVE / rainbow.length))]);
            blocks.add(b);
        }
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
        return Configuration.DEFAULT_PADDLE_WIDTH * 3;
    }
}
