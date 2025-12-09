// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: LevelOnceBackground.
 * Description: LevelOnceBackground Class.
 */
public class LevelOneBackground implements Sprite {
    /**
     * Name: drawOn
     * Description: draw the sprite to the screen.
     *
     * @param d the Draw Surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        int centerX = LevelOne.BLOCK_TOP_LEFT.getIntX() + LevelOne.BLOCK_SIZE / 2;
        int centerY = LevelOne.BLOCK_TOP_LEFT.getIntY() + LevelOne.BLOCK_SIZE / 2;
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HIGHT);
        d.setColor(Color.RED);
        // Circles Target
        for (int i = 0; i < 3; i++) {
            d.drawCircle(centerX, centerY, LevelOne.BLOCK_SIZE * (i + 2));
        }
        // Lines |
        d.drawLine(centerX, centerY - LevelOne.BLOCK_SIZE * 3, centerX,
                centerY + LevelOne.BLOCK_SIZE * 3);
        // Lines _
        d.drawLine(centerX - LevelOne.BLOCK_SIZE * 3, centerY, centerX + LevelOne.BLOCK_SIZE * 3,
                centerY);

    }

    /**
     * Name: timePassed
     * Description: do the action that our object will do when time pass.
     */
    public void timePassed() {

    }

    /**
     * Name: addToGame
     * Description: Add the sprite object to the game.
     *
     * @param g the game that we want to add the sprite object to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
