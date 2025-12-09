// 318844677 Netanel Berkovits

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * Name: GameFlow.
 * Description: GameFlow Class.
 */
public class GameFlow {

    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;

    private final Counter score;

    /**
     * Name: GameFlow
     * Description: A constructor for game flow.
     *
     * @param ar our animation runner
     * @param ks our keyboard.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.runner = ar;
        this.keyboard = ks;
        this.score = new Counter();
    }

    /**
     * Name: GameFlow
     * Description: A constructor for game flow.
     *
     * @param windowName      our animation runner
     * @param width           the width of the game
     * @param height          the height of the game
     * @param framesPerSecond how many frames we want per second.
     */
    public GameFlow(String windowName, int width, int height, int framesPerSecond) {
        GUI gui = new GUI(windowName, width, height);
        this.runner = new AnimationRunner(gui, framesPerSecond);
        this.keyboard = gui.getKeyboardSensor();
        this.score = new Counter();
    }

    /**
     * Name: runLevels
     * Description: Run the levels in order.
     *
     * @param levels the levels we want to play.
     */
    public void runLevels(List<LevelInformation> levels) {
        boolean isWinning = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score);

            level.initialize();

            int ballsAtTheEndOfTheLevel = level.run();
            if (ballsAtTheEndOfTheLevel <= 0) {
                isWinning = false;
                break;
            }
        }
        this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                new GameOverScreen(isWinning, this.score)));
        close();
    }

    /**
     * Name: close
     * Description: close the game.
     */
    private void close() {
        this.runner.close();
    }
}
