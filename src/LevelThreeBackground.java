// 318844677 Netanel Berkovits


import Geometry.Point;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Name: LevelThreeBackground.
 * Description: LevelThreeBackground Class.
 */
public class LevelThreeBackground implements Sprite {

    private final int numberOfBalls;
    private static final int MIN_RADIUS = 20;
    private static final int MAX_RADIUS = 120;
    // Circle
    private final List<Point> centers;
    private final List<Integer> radius;
    private final List<Color> colors;
    private final List<Boolean> isGrowing;

    private final Color[] rainbow =
            {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, new Color(177, 0, 255), Color.MAGENTA};


    /**
     * Name: random
     * Description: generate a random int number.
     *
     * @param min the minimal number that will be generated.
     * @param max the maximum number that will be generated.
     * @return a random int number.
     */
    private static int random(int min, int max) {
        return (new Random()).nextInt(max) + min;
    }

    /**
     * Name: LevelThreeBackground
     * Description: constructor for LevelThreeBackground.
     *
     * @param numberOfBalls number of balls in the background;
     */
    public LevelThreeBackground(int numberOfBalls) {
        this.numberOfBalls = numberOfBalls;
        // creation
        this.centers = new ArrayList<Point>();
        this.radius = new ArrayList<Integer>();
        this.colors = new ArrayList<Color>();
        this.isGrowing = new ArrayList<Boolean>();
        // initialisation
        for (int i = 0; i < numberOfBalls; i++) {
            this.centers.add(new Point(random(0, Configuration.SCREEN_WIDTH),
                    random(0, Configuration.SCREEN_HIGHT)));
            this.radius.add(random(MIN_RADIUS, MAX_RADIUS));
            this.colors.add(rainbow[random(0, rainbow.length)]);
            this.isGrowing.add(random(0, 1) == 1);
        }
    }

    /**
     * Name: drawOn
     * Description: draw the sprite to the screen.
     *
     * @param d the Draw Surface we want to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(0, 0, 0));
        d.fillRectangle(0, 0, Configuration.SCREEN_WIDTH, Configuration.SCREEN_HIGHT);

        for (int i = 0; i < this.numberOfBalls; i++) {
            d.setColor(this.colors.get(i));
            d.drawCircle(this.centers.get(i).getIntX(), this.centers.get(i).getIntY(), this.radius.get(i));
        }

    }

    /**
     * Name: timePassed
     * Description: do the action that our object will do when time pass.
     */
    public void timePassed() {
        for (int i = 0; i < this.numberOfBalls; i++) {
            this.radius.set(i, this.radius.get(i) + (this.isGrowing.get(i) ? 1 : -1));
            // need to stop growing
            if (this.radius.get(i) > MAX_RADIUS) {
                this.isGrowing.set(i, false);
                this.colors.set(i, rainbow[random(0, rainbow.length)]);
            }
            // need to stop shrink
            if (this.radius.get(i) < MIN_RADIUS) {
                this.isGrowing.set(i, true);
                this.colors.set(i, rainbow[random(0, rainbow.length)]);
            }
        }
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
