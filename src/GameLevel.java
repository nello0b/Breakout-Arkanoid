// 318844677 Netanel Berkovits


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import Geometry.Velocity;

/**
 * Name: Game Class
 * Description: A Game object, this is the game it self!!!!.
 */
public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;

    private final Counter ballsCounter;

    private final Counter blocksCounter;
    private final Counter scoreCounter;

    private final AnimationRunner runner;

    private final LevelInformation info;

    private final KeyboardSensor keyboard;

    private boolean running;

    private boolean cheatAlreadyPressed;

    /**
     * Name: GameLevel
     * Description: A constructor for GameLevel.
     *
     * @param info            the information about our level
     * @param keyboardSensor  the keyboard of the game
     * @param animationRunner the game's animation runner
     * @param score           the score counter
     */
    public GameLevel(LevelInformation info, KeyboardSensor keyboardSensor, AnimationRunner animationRunner,
                     Counter score) {
        this.info = info;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.ballsCounter = new Counter();
        this.blocksCounter = new Counter();
        this.scoreCounter = score;
        this.keyboard = keyboardSensor;
        this.runner = animationRunner;
    }

    /**
     * Name: getBlocksCounter
     * Description: A getter for blocksCounter.
     *
     * @return blocksCounter.
     */
    public Counter getBlocksCounter() {
        return this.blocksCounter;
    }

    /**
     * Name: getBallsCounter
     * Description: A getter for ballsCounter.
     *
     * @return ballsCounter.
     */
    public Counter getBallsCounter() {
        return this.ballsCounter;
    }

    /**
     * Name: getScoreCounter
     * Description: A getter for scoreCounter.
     *
     * @return scoreCounter.
     */
    public Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * Name: getSprites
     * Description: A getter for Sprite.SpriteCollection.
     *
     * @return our Sprite.SpriteCollection.
     */
    private SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Name: getSprites
     * Description: A getter for Hit.GameEnvironment.
     *
     * @return our Hit.GameEnvironment.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Name: addCollidable
     * Description: add a Hit.Collidable object to our environment.
     *
     * @param c the object we want to add.
     */
    public void addCollidable(Collidable c) {
        getEnvironment().addCollidable(c);
    }

    /**
     * Name: addSprite
     * Description: add a Sprite.Sprite object to our collection.
     *
     * @param s the sprite we want to add.
     */
    public void addSprite(Sprite s) {
        getSprites().addSprite(s);
    }

    /**
     * Name: initialize
     * Description: Initialize a new game: create the Blocks and GameElements.
     * Ball (and GameElements.Paddle) and add them to the game.
     */
    public void initialize() {
        Level level = new Level(this.info, this.keyboard);

        // Listeners
        // ball remover
        BallRemover ballRemover = new BallRemover(this, getBallsCounter());
        level.addHitListenerToBlock(ballRemover);
        // block remover
        BlockRemover blockRemover = new BlockRemover(this, getBlocksCounter());
        level.addHitListenerToBlock(blockRemover);
        // score tracker
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(getScoreCounter());
        level.addHitListenerToBlock(scoreTrackingListener);
        // Score Indicator
        level.getGameInfo().setScoreCounter(getScoreCounter());
        // Ball Counter
        getBallsCounter().increase(level.getBalls().size());
        level.add(this);
    }

    /**
     * Name: run
     * Description: Run the game -- start the animation loop.
     *
     * @return how many balls we had at the end of the level
     */
    public int run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        this.runner.run(new CountdownAnimation(Configuration.SECONDS_TO_COUNT, Configuration.COUNT_DOWN_FROM,
                getSprites())); // countdown before turn starts.
        // the game.
        this.runner.run(this);
        if (getBallsCounter().getValue() > 0) {
            getScoreCounter().increase(100);
        }
        //delay before the game close
        return getBallsCounter().getValue();
    }

    /**
     * Name: removeCollidable
     * Description: remove a give collidable from the game.
     *
     * @param c the collidable we want to remove from the game.
     */
    public void removeCollidable(Collidable c) {
        getEnvironment().removeCollidable(c);
    }

    /**
     * Name: removeSprite
     * Description:  remove a give sprite from the game.
     *
     * @param s the sprite we want to remove from the game.
     */
    public void removeSprite(Sprite s) {
        getSprites().removeSprite(s);
    }

    /**
     * Name: doOneFrame
     * Description: Description.
     *
     * @param d Description text text text.
     */
    public void doOneFrame(DrawSurface d) {
        // draw sprites
        getSprites().drawAllOn(d);
        // move everything
        getSprites().notifyAllTimePassed();
        if (this.keyboard.isPressed("q")) {
            if (!this.cheatAlreadyPressed) {
                activateCheatBalls();
            }
            this.cheatAlreadyPressed = true;
        } else {
            this.cheatAlreadyPressed = false;
        }
        //pose the game
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        // the game should stop if you have no more lives
        if (getBallsCounter().getValue() <= 0) {
            this.running = false;
        }
        // the game should stop because there are no more blocks
        if (getBlocksCounter().getValue() <= 0) {
            this.running = false;
        }
    }

    /**
     * Name: shouldStop
     * Description: Check if the game should stop.
     *
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Name: activateCheatBalls
     * Description: spawn additional balls with different starting angles.
     */
    private void activateCheatBalls() {
        int ballsToCreate = 10;
        int angle = Configuration.OPENING_ANGLE;
        double angleD = (double) (2 * angle) / (ballsToCreate - 1);
        double speed = Configuration.DEFAULT_SPEED / 2.0;
        int radius = Configuration.BALL_RADIUS;
        double x = (double) Configuration.SCREEN_WIDTH / 2;
        double y = Configuration.SCREEN_HIGHT - Configuration.BOARDER_SIZE * 2 - radius;
        for (int i = 0; i < ballsToCreate; i++) {
            Ball ball = new Ball(x, y, radius, Configuration.BALL_COLOR);
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle - (angleD * i), speed));
            addBallToGame(ball);
        }
    }

    /**
     * Name: addBallToGame
     * Description: add a ball to the current running game and update counters.
     *
     * @param ball the ball we want to add.
     */
    private void addBallToGame(Ball ball) {
        ball.setGameEnvironment(getEnvironment());
        addSprite(ball);
        getBallsCounter().increase(1);
    }
}