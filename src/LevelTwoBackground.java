// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: LevelTwoBackground.
 * Description: LevelTwoBackground Class.
 */
public class LevelTwoBackground implements Sprite {


    /**
     * Name: drawOn
     * Description: draw the sprite to the screen.
     *
     * @param d the Draw Surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        int centerX = LevelTwo.SUN_CENTER.getIntX();
        int centerY = LevelTwo.SUN_CENTER.getIntY();
        Color[] sunColors = {new Color(251, 255, 175), new Color(255, 226, 81), new Color(255, 204, 0)};

        d.setColor(new Color(0, 255, 238));
        d.fillRectangle(0, 0, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HIGHT);

        // Shine
        int shine = 80;
        int dif = (int) Math.ceil((double) (Configuration.SCREEN_WIDTH - (Configuration.BOARDER_SIZE * 2)) / shine);
        for (int i = 0; i < shine; i++) {
            d.setColor(sunColors[(i) % sunColors.length]);
            d.drawLine(centerX, centerY, LevelTwo.BLOCK_TOP_LEFT.getIntX() + (dif * i),
                    LevelTwo.BLOCK_TOP_LEFT.getIntY());
        }
        // Sun
        for (int i = 0; i < 3; i++) {
            d.setColor(sunColors[i]);
            d.fillCircle(centerX, centerY, LevelTwo.SUN_RADIUS - (i * 10));
        }

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
