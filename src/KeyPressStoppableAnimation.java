// 318844677 Netanel Berkovits


import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Name: KeyPressStoppableAnimation.
 * Description: KeyPressStoppableAnimation Class.
 */
public class KeyPressStoppableAnimation implements Animation {

    private boolean isAlreadyPressed;
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;

    private boolean shouldStop;

    /**
     * Name: KeyPressStoppableAnimation
     * Description: A constructor for KeyPressStoppableAnimation.
     *
     * @param sensor    the keyboard
     * @param key       the key we want to press to stop
     * @param animation the animation we want to wrap
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        shouldStop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Name: doOneFrame
     * Description: Do all the logic that need to happened in 1 frame.
     *
     * @param d the draw surface we want to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key) && !this.isAlreadyPressed) {
            this.shouldStop = true;
        }
        this.isAlreadyPressed = this.keyboard.isPressed(key);
    }

    /**
     * Name: shouldStop
     * Description: check if the game should stop.
     *
     * @return if the game should stop.
     */
    public boolean shouldStop() {
        return this.shouldStop;
    }
}
