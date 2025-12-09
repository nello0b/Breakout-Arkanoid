// 318844677 Netanel Berkovits

import Geometry.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Name: GameInfo
 * Description: A GameInfo object.
 */
public class GameInfo implements Sprite {

    private final Rectangle rect;
    private Counter score;
    private String levelName;
    private Color color;

    /**
     * Name: GameInfo
     * Description: A constructor for GameInfo.
     *
     * @param rect the place of the GameInfo.
     */
    public GameInfo(Rectangle rect) {
        this.rect = rect;
        setLevelName("default_name");
    }

    /**
     * Name: setColor
     * Description: setter for the color.
     *
     * @param color the color we want to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Name: getLevelNames
     * Description: getter for the levelNames.
     *
     * @return return the block's color.
     */
    public String getLevelName() {
        return this.levelName;
    }

    /**
     * Name: getColor
     * Description: getter for the block's color.
     *
     * @return return the block's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Name: setScoreCounter
     * Description: A setter for the score counter.
     *
     * @param score the counter we want to set.
     */
    public void setScoreCounter(Counter score) {
        this.score = score;
    }

    /**
     * Name: setLevelName
     * Description: A setter for the name of the level.
     *
     * @param levelName the name of the level we want to set.
     */
    public void setLevelName(String levelName) {
        this.levelName = new String(levelName);
    }


    /**
     * Name: getRectangle
     * Description: A getter for rectangle.
     *
     * @return the point of the GameElements.ScoreIndicator.
     */
    public Rectangle getRectangle() {
        return this.rect;
    }

    /**
     * Name: drawOn
     * Description: draw the sprite to the screen.
     *
     * @param d the Draw Surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {

        int forntSize = (int) (getRectangle().getHeight() * 0.6);
        // Box
        getRectangle().drawOn(d, Color.BLACK, getColor());
        //Text
        d.setColor(Color.BLACK);
        int x = (int) (getRectangle().getUpperLeft().getIntX() + getRectangle().getWidth() / 10);
        int y = (int) ((getRectangle().getUpperLeft().getY() + getRectangle().getHeight()) + forntSize) / 2;
        String str;
        // Score
        str = "Score : " + this.score.getValue();
        d.drawText(x, y, str, forntSize);
        // Level Name
        x = x + (int) (str.length() * forntSize * 0.75);
        str = "Level Name : " + this.levelName;
        d.drawText(x, y, str, forntSize);
    }

    /**
     * Name: timePassed
     * Description: do the action that our object will do when time pass.
     */
    public void timePassed() {
        //Does Nothing
    }

    /**
     * Name: addToGame
     * Description: Add the scoreIndicator to the game.
     *
     * @param gameLevel the game that we want to add the scoreIndicator to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
