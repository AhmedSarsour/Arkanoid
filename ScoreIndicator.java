import java.awt.Color;

import biuoop.DrawSurface;

/**
 * ScoreIndicator.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class ScoreIndicator extends Block {
    private GameLevel game;

    // ScoreIndicator construct, the same essence as of that of a block, but has
    // access to ScoreTrackingListener.
    /**
     * @param rect the shape.
     * @param color the color.
     * @param durability the hit points.
     * @param score the Listener.
     */
    public ScoreIndicator(Rectangle rect, Color color, int durability,
            ScoreTrackingListener score) {
        super(rect, color, durability);
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 20);
        d.drawRectangle(0, 0, 800, 20);
        d.setColor(java.awt.Color.black);
        //drawing the score on the middle of the upper part of the GUI.
        d.drawText(360, 15,
                "Score: " + String
                        .valueOf(this.game.getScore().getScoreVal()),
                20);
    }

    // getting the game in which the score will be displayed on.
    /**
     * @param g the game.
     */
    public void setTheGame(GameLevel g) {
        this.game = g;
    }
}
