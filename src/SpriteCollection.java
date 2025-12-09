// 318844677 Netanel Berkovits


import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: Sprite.SpriteCollection Class
 * Description: A Sprite.SpriteCollection object store a Collection of Sprites.
 */
public class SpriteCollection {

    private final List<Sprite> sprites;

    /**
     * Name: Sprite.SpriteCollection
     * Description: A constructor for Sprite.SpriteCollection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Name:getSprites
     * Description: A getter for our sprites.
     *
     * @return our sprites list.
     */
    private List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Name:addSprite
     * Description: Add a sprite to our sprite list.
     *
     * @param s the sprite we want to add to the sprite list.
     */
    public void addSprite(Sprite s) {
        getSprites().add(s);
    }

    /**
     * Name:removeSprite
     * Description: remove a sprite to our sprite list.
     *
     * @param s the sprite we want to remove to the sprite list.
     */
    public void removeSprite(Sprite s) {
        getSprites().remove(s);
    }

    /**
     * Name: notifyAllTimePassed
     * Description: Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(getSprites());
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * Name: drawAllOn
     * Description: Call drawOn(d) on all sprites.
     *
     * @param d the draw surface we want to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<Sprite>(getSprites());
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
