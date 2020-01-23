import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

/**
 * HighScoresAnimation.
 * @author ahmed.
 * @since 15.06.2017.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;
    private String endingKey;
    private GUI gui;
    private boolean setAndGo;

    // HighScoresAnimation constructor, accepts the score table to display, and
    // the endKey to end the animation and lastly accepts the gui.
    /**
     * @param scores a List.
     * @param endKey to end the animation.
     * @param theGui GUI.
     */
    public HighScoresAnimation(HighScoresTable scores, String endKey,
            GUI theGui) {
        this.scores = scores;
        this.endingKey = endKey;
        this.setAndGo = true;
        this.gui = theGui;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        while (true) {
            d = gui.getDrawSurface();
            d.setColor(java.awt.Color.black);
            d.fillRectangle(0, 0, 800, 600);
            d.setColor(java.awt.Color.orange);
            d.drawText(270, 32, "--HIGH-SCORES--", 32);
            d.setColor(java.awt.Color.yellow);
            for (int i = 1; i <= this.scores.getHighScores()
                    .size(); i++) {
                d.drawText(100, i * 32 + 50, this.scores
                        .getHighScores().get(i - 1).getName(), 32);
                d.drawText(400, i * 32 + 50, ":", 32);
                d.drawText(680, i * 32 + 50,
                        String.valueOf(this.scores.getHighScores()
                                .get(i - 1).getScore()),
                        32);
            }
            // if the space key is pressed, ending the display of the endScreen.
            if (keyboard.isPressed(this.endingKey)) {
                this.setAndGo = false;
                break;
            }
            // drawing the current state of the level.
            gui.show(d);
            sleeper.sleepFor(50);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.setAndGo;
    }
}