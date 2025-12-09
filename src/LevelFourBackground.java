// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: LevelFourBackground.
 * Description: LevelFourBackground Class.
 */
public class LevelFourBackground implements Sprite {

    private double phase;

    /**
     * Name: LevelFourBackground
     * Description: A constructor for LevelFourBackground.
     */
    public LevelFourBackground() {
        this.phase = 0;
    }

    /**
     * Name: drawOn
     * Description: draw the sprite to the screen.
     *
     * @param d the Draw Surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HIGHT);
        Color[] rainbow =
                {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(177, 0, 255), Color.MAGENTA};
        d.setColor(rainbow[(int) (this.phase % rainbow.length)]);
        int fontSize = 50;
        String str = "THANK YOU FOR PLAYING !!!";
        int x = Configuration.BOARDER_SIZE * 3;
        int y = (Configuration.SCREEN_HIGHT + Configuration.BOARDER_SIZE * 2) / 2 - 150;
        d.drawText(x, y, str, fontSize);
    }

    /**
     * Name: timePassed
     * Description: do the action that our object will do when time pass.
     */
    public void timePassed() {
        this.phase += 0.05;
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
