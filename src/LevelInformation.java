// 318844677 Netanel Berkovits


import Geometry.Velocity;

import java.util.List;

/**
 * Name: LevelInformation.
 * Description: LevelInformation interface.
 */
public interface LevelInformation {
    /**
     * Name: numberOfBalls
     * Description: The number of balls in the level.
     *
     * @return the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * Name: initialBallVelocities
     * Description: The initial velocity of each ball,
     * note that initialBallVelocities().size() == numberOfBalls().
     *
     * @return return the velocity of each ball.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Name: paddleSpeed
     * Description: The speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * Name: paddleWidth
     * Description: The width of the paddle.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * Name: levelName
     * Description: The name of the level.
     *
     * @return the name of the level.
     */
    String levelName();


    /**
     * Name: getBackground
     * Description: The background of the level.
     *
     * @return the background of the level.
     */
    Sprite getBackground();


    /**
     * Name: blocks
     * Description: The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level, each block contains its size, color and location.
     */
    List<Block> blocks();

    /**
     * Name: numberOfBlocksToRemove
     * Description: The Number of blocks that should be removed before the level is considered to be "cleared"
     * This number should be <= blocks.size();.
     *
     * @return the Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();

    /**
     * Name: puddleSize
     * Description: The size of the puddle.
     *
     * @return the size of the puddle.
     */
    int puddleSize();
}