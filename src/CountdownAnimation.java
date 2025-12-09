// 318844677 Netanel Berkovits

// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.

import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: CountdownAnimation
 * Description: A CountdownAnimation object.
 */
public class CountdownAnimation implements Animation {
    private final double numOfSeconds;
    private final int countFrom;
    private final SpriteCollection gameScreen;

    private final double creationTime;

    /**
     * Name: CountdownAnimation
     * Description: CountdownAnimation counteract.
     *
     * @param numOfSeconds how many seconds we want the countdown to be in IRL time.
     * @param countFrom    the number of seconds we want to count from
     * @param gameScreen   all the sprites we want to show during the countdown
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.creationTime = System.currentTimeMillis();
    }

    /**
     * Name: doOneFrame
     * Description: Do all the logic that need to happened in 1 frame.
     *
     * @param d the draw surface we want to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        double width = Configuration.SCREEN_WIDTH;
        double height = Configuration.BOARDER_SIZE * 2;
        Rectangle rect = new Rectangle(0, 0, width, height);
        this.gameScreen.drawAllOn(d);
        rect.drawOn(d, Color.BLACK, Color.WHITE);

        int forntSize = (int) (height * 0.75);
        String str;
        // Lives
        str = "Game Starts In: " + (int) Math.ceil(
                countFrom - (numOfSeconds / countFrom) * ((System.currentTimeMillis() - creationTime) / 1000));
        int x = (int) (width - (str.length() * forntSize * 0.5)) / 2;
        int y = (int) (height + forntSize) / 2;
        d.drawText(x, y, str, forntSize);

    }

    /**
     * Name: shouldStop
     * Description: check if the game should stop.
     *
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return this.creationTime + (1000 * numOfSeconds) <= System.currentTimeMillis();
    }


}