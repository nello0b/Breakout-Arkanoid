// 318844677 Netanel Berkovits

import biuoop.DrawSurface;

/**
 * Name: Sprite.Sprite interface.
 */
public interface Sprite {

    /**
     * Name: drawOn
     * Description: draw the sprite to the screen.
     *
     * @param d the Draw Surface we want to draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * Name: timePassed
     * Description: do the action that our object will do when time pass.
     */
    void timePassed();

    /**
     * Name: addToGame
     * Description: Add the sprite object to the game.
     *
     * @param g the game that we want to add the sprite object to.
     */
    void addToGame(GameLevel g);
}