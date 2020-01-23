import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * SpriteCollection.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class SpriteCollection {
    private List<Sprite> allSprites;

    // creating list of sprites.
    /**
     * SpriteCollection constructor.
     */
    public SpriteCollection() {
        this.allSprites = new ArrayList<Sprite>();
    }

    // adding a Sprite to the spriteCollection.
    /**
     * addSprite function.
     * @param s sprite.
     */
    public void addSprite(Sprite s) {
        this.allSprites.add(s);
    }

    //removes a given sprite from the sprite-collection list.
    /**
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        List<Sprite> sprites = new ArrayList<Sprite>(this.allSprites);
        sprites.remove(s);
        this.allSprites = sprites;
    }

    // call timePassed() on all sprites.
    /**
     * notifyAllTimePassed function.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.allSprites.size(); i++) {
            this.allSprites.get(i).timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    /**
     * DrawAllOn function.
     * @param d the drawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.allSprites.size(); i++) {
            this.allSprites.get(i).drawOn(d);
        }
    }

    // get the number of all the sprites in the game.
    /**
     * @return number of all the sprites.
     */
    public int getSize() {
        return this.allSprites.size();
    }

    // getting a specific sprite in the spriteCollection.
    /**
     * getSprite function.
     * @param index of a sprite.
     * @return Sprite.
     */
    public Sprite getSprite(int index) {
        return this.allSprites.get(index);
    }

    // getting the paddle.
    /**
     * \
     * getPaddle function.
     * @return Paddle Sprite.
     */
    public Sprite getPaddle() {
        return this.allSprites.get(0);
    }

    // getting the first ball.
    /**
     * \
     * getBall1 function.
     * @return ball Sprite.
     */

}