import java.awt.Color;

import biuoop.DrawSurface;

/**
 * LivesIndicator.
 * @author ahmed.
 *         since 20.6.2017.
 */
public class LivesIndicator extends Block {
    private GameLevel game;

    /**
     * LivesIndicator constructor.
     * @param rect the shape.
     * @param color of block.
     * @param durability of block.
     */
    public LivesIndicator(Rectangle rect, Color color,
            int durability) {
        super(rect, color, durability);
    }

    // this class, is an extended block that differs by the drawOn method.
    /**
     * drawOn function.
     * @param d the drawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillRectangle(200, 0, 100, 20);
        d.drawRectangle(0, 0, 100, 20);
        d.setColor(java.awt.Color.black);
        d.drawText(200, 15,
                "Lives: " + String.valueOf(this.game.getLives()), 20);
    }

    // getting the game in which the LivesIndicator block will be shown on.
    /**
     * setTheGame function.
     * @param g the GameLevel.
     */
    public void setTheGame(GameLevel g) {
        this.game = g;
    }
}