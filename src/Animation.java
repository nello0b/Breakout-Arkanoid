// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

/**
 * Name: Animation.
 * Description: Animation interface.
 */
public interface Animation {
    /**
     * Name: doOneFrame
     * Description: Do all the logic that need to happened in 1 frame.
     *
     * @param d the draw surface we want to draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Name: shouldStop
     * Description: check if the game should stop.
     *
     * @return if the game should stop.
     */
    boolean shouldStop();
}