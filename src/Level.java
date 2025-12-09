// 318844677 Netanel Berkovits


import Geometry.Rectangle;
import Geometry.Velocity;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Name: GameElements.Level Class
 * Description: A GameElements.Level object.
 */
public class Level {

    private final Sprite background;
    private final List<Ball> balls;
    private final List<Paddle> paddles;
    private final List<Block> blocks;

    private final GameInfo gameInfo;

    private final LevelInformation info;

    private final KeyboardSensor keyboard;

    /**
     * Name: GameElements.Level
     * Description: A constructor for GameElements.Level.
     *
     * @param info     the information of our level.
     * @param keyboard the keyboard sensor of the level.
     */
    public Level(LevelInformation info, KeyboardSensor keyboard) {

        //Lists
        this.balls = new ArrayList<Ball>();
        this.paddles = new ArrayList<Paddle>();
        this.blocks = new ArrayList<Block>();
        // NOT lists
        this.keyboard = keyboard;
        this.info = info;

        // Initialize:
        // balls
        for (Ball b : defaultBalls(info.numberOfBalls())) {
            add(b);
        }
        // paddle
        add(defaultPaddle(info.puddleSize()));
        // blocks
        this.blocks.addAll(defaultBorders());
        for (Block b : info.blocks()) {
            add(b);
        }
        // gameInfo
        this.gameInfo = defaultGameInfo(info.levelName());
        // background
        this.background = info.getBackground();


    }

    /**
     * Name: defaultGameInfo
     * Description: the default game info.
     *
     * @param levelName the name of the level
     * @return the default game info.
     */
    public GameInfo defaultGameInfo(String levelName) {
        // Parameters
        int length = Configuration.BOARDER_SIZE;
        int width = Configuration.SCREEN_WIDTH;

        GameInfo gi = new GameInfo(new Rectangle(0, 0, width, length * 2));
        gi.setColor(Configuration.GAME_INFO_COLOR);
        gi.setLevelName(levelName);

        return gi;
    }


    /**
     * Name: defaultBalls
     * Description: the balls of our game.
     *
     * @param num the number of balls in the level.
     * @return the default balls.
     */
    public List<Ball> defaultBalls(int num) {
        List<Ball> balls = new ArrayList<Ball>();
        // Parameters
        int length = Configuration.BOARDER_SIZE;
        int width = Configuration.SCREEN_WIDTH;
        int height = Configuration.SCREEN_HIGHT;
        double speed = Configuration.DEFAULT_SPEED;
        Color ballColor = Configuration.BALL_COLOR;
        int radius = Configuration.BALL_RADIUS;

        int x = width / 2;
        int y = height - length * 2 - radius;
        int angle = Configuration.OPENING_ANGLE;
        if (num == 1) {
            balls.add(new Ball(x, y, radius, ballColor));
            balls.get(0).setVelocity(Velocity.fromAngleAndSpeed(0, speed / 2));
            return balls;
        }
        int angleD = (int) Math.ceil((double) (2 * angle) / (num - 1));
        for (int i = 0; i < num; i++) {
            balls.add(new Ball(x, y, radius, ballColor));
            balls.get(i).setVelocity(Velocity.fromAngleAndSpeed(angle - (angleD * i), speed / 2));
        }
        return balls;
    }

    /**
     * Name: defaultPaddle
     * Description: the paddle of our game.
     *
     * @param paddleWidth the width of the paddle
     * @return the default paddle.
     */
    public Paddle defaultPaddle(int paddleWidth) {
        // Parameters
        int length = Configuration.BOARDER_SIZE;
        int width = Configuration.SCREEN_WIDTH;
        int height = Configuration.SCREEN_HIGHT;
        double speed = Configuration.DEFAULT_SPEED;
        Color paddleColor = Configuration.PADDLE_COLOR;
        Paddle paddle =
                new Paddle(((double) (width - paddleWidth) / 2), height - length * 2, paddleWidth, length,
                        this.keyboard, paddleColor);
        paddle.setBounceMode(Configuration.PADDLE_BOUNCE_MODE);
        paddle.setSpeed(speed * 0.75);

        return paddle;
    }

    /**
     * Name: defaultBorders
     * Description: the boarders of our game.
     *
     * @return the default borders.
     */
    public static List<Block> defaultBorders() {
        List<Block> blocks = new ArrayList<Block>();
        // Parameters
        int length = Configuration.BOARDER_SIZE;
        int height = Configuration.SCREEN_HIGHT;
        int width = Configuration.SCREEN_WIDTH;
        Color borderColor = Configuration.BORDERS_COLOR;
        Color borderColorKiller = Configuration.BORDERS_COLOR_KILLER;
        // Left
        blocks.add(new Block(length * -1, length * -1, length * 2, height + length * 2, borderColor));
        // Right
        blocks.add(new Block(width - length, length * -1, length * 2, height + length * 2, borderColor));
        // Top
        blocks.add(new Block(length, length * -1, width - length * 2, length * 4, borderColor));
        // Bottom
        Block bottom = new Block(length, height, width - length * 2, length * 2, borderColorKiller);
        bottom.setProperty("Ball_Killer", true);
        blocks.add(bottom);
        return blocks;
    }

    /**
     * Name: getBackground
     * Description: A getter for game background.
     *
     * @return our game background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Name: getGameInfo
     * Description: A getter for game info.
     *
     * @return our game info.
     */
    public GameInfo getGameInfo() {
        return this.gameInfo;
    }

    /**
     * Name: getBalls
     * Description: A getter for Balls.
     *
     * @return our Balls List.
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Name: getPaddles
     * Description: A getter for Paddles.
     *
     * @return our Paddles List.
     */
    public List<Paddle> getPaddles() {
        return paddles;
    }

    /**
     * Name: getBlocks
     * Description: A getter for Blocks.
     *
     * @return our Blocks list.
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /**
     * Name: addBlock
     * Description: add a block to the Level.
     *
     * @param b the block we want to add.
     */
    public void add(Block b) {
        this.blocks.add(b);
    }

    /**
     * Name: addBlock
     * Description: add a ball to the level.
     *
     * @param b the ball we want to add.
     */
    public void add(Ball b) {
        this.balls.add(b);
    }

    /**
     * Name: addPaddle
     * Description: add a paddle to the GameElements.Level.
     *
     * @param p the paddle we want to add.
     */
    public void add(Paddle p) {
        this.paddles.add(p);
    }

    /**
     * Name: addHitListenerToBlock
     * Description: Add a HitListener to all the blocks in the level.
     *
     * @param listener Description text text text.
     */
    public void addHitListenerToBlock(HitListener listener) {
        for (Block block : getBlocks()) {
            listener.addToBlock(block);
        }
    }

    /**
     * Name: addToGame
     * Description: add all the elements of the level to the game.
     *
     * @param g the game we want to add the level to.
     */
    public void add(GameLevel g) {
        getBackground().addToGame(g);
        for (Block b : getBlocks()) {
            b.addToGame(g);
        }
        for (Paddle p : getPaddles()) {
            p.addToGame(g);
        }
        for (Ball b : getBalls()) {
            b.addToGame(g);
        }
        getGameInfo().addToGame(g);

    }
}
