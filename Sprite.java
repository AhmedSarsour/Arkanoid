import biuoop.DrawSurface;

/**
 * Sprite interface.
 * @author ahmed.
 */
public interface Sprite {
    // draw the sprite to the screen
    /**
     * drawOn function.
     * @param d surface.
     */
    void drawOn(DrawSurface d);

    // notify the sprite that time has passed
    /**
     * timePassed function.
     */
    void timePassed();
}