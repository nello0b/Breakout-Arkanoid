// 318844677 Netanel Berkovits

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * Name: AnimationRunner.
 * Description: A AnimationRunner object.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;

    /**
     * Name: AnimationRunner
     * Description: A constructor for AnimationRunner.
     *
     * @param gui             the gui object of our animation.
     * @param framesPerSecond how many frames we want per second.
     */
    public AnimationRunner(GUI gui, int framesPerSecond) {
        this.gui = gui;
        this.framesPerSecond = framesPerSecond;
    }

    /**
     * Name: AnimationRunner
     * Description: A constructor for AnimationRunner.
     *
     * @param windowName      the title at the top of the window
     * @param width           the width of the game
     * @param height          the height of the game
     * @param framesPerSecond how many frames we want per second.
     */
    public AnimationRunner(String windowName, int width, int height, int framesPerSecond) {
        this(new GUI(windowName, width, height), framesPerSecond);
    }

    /**
     * Name: Name
     * Description: Description.
     *
     * @param animation Description text text text.
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Name: close
     * Description: close the game.
     */
    public void close() {
        this.gui.close();
    }
}