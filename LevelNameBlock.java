import java.awt.Color;

import biuoop.DrawSurface;

/**
 * LevelNameBlock.
 * @author ahmed.
 *         since 20.6.2017.
 */
public class LevelNameBlock extends Block {
    private GameLevel game;

    /**
     * LevelNameBlock constructor.
     * @param rect the shape.
     * @param color of block.
     * @param durability of block.
     */
    public LevelNameBlock(Rectangle rect, Color color,
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
        d.setColor(java.awt.Color.black);
        d.drawText(520, 15, "Level Name: " + this.game.getLevelName(),
                20);
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
