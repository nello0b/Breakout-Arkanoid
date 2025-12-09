// 318844677 Netanel Berkovits


import Geometry.Point;
import Geometry.Rectangle;
import Geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: LevelThree.
 * Description: LevelThree Class.
 */
public class LevelThree implements LevelInformation {

    private static final int NUMBER_OF_BALLS = 2;
    private static final String LEVEL_NAME = "Instant Classic";

    private static final int BLOCK_WIDTH =
            (int) Math.round((double) (Configuration.SCREEN_WIDTH - (Configuration.BOARDER_SIZE * 2)) / 15);
    public static final Point BLOCK_TOP_LEFT =
            new Point(Configuration.SCREEN_WIDTH - Configuration.BOARDER_SIZE - BLOCK_WIDTH,
                    Configuration.BOARDER_SIZE * 6);
    public static final int ROWS = 5;
    public static final int COLUMNS = 10;

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
        return new LevelThreeBackground(40);
    }


    /**
     * Name: blocks
     * Description: The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        // Parameters
        Block b;
        int x = BLOCK_TOP_LEFT.getIntX();
        int y = BLOCK_TOP_LEFT.getIntY();
        int height = (int) (Configuration.BOARDER_SIZE * 1.5);
        Color[] rainbow =
                {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(177, 0, 255), Color.MAGENTA};
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS - i; j++) {
                b = new Block(new Rectangle(x - (BLOCK_WIDTH * j), y + (height * i), BLOCK_WIDTH,
                        height));
                b.setProperty("Destructible", true);
                b.setProperty("Scorable", true);
                b.setBoarder(rainbow[(i + j + 2) % rainbow.length]);
                b.setColor(Color.BLACK);
                blocks.add(b);
            }
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
        return Configuration.DEFAULT_PADDLE_WIDTH;
    }
}
