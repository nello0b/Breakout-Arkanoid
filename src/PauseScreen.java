
// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

/**
 * Name: PauseScreen Class
 * Description: A PauseScreen object, this is the game it self!!!!.
 */
public class PauseScreen implements Animation {

    /**
     * Name: doOneFrame
     * Description: Do all the logic that need to happened in 1 frame.
     *
     * @param d the draw surface we want to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(Configuration.BOARDER_SIZE * 4, Configuration.SCREEN_HIGHT / 2, "paused -- press space to continue",
                Configuration.BOARDER_SIZE * 2);
    }

    /**
     * Name: shouldStop
     * Description: check if the game should stop.
     *
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return false;
    }
}