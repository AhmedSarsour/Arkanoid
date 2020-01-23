import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * PauseScreen.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    // PauseScreen construct, applies only getting the keyboard, and setting the
    // STOP-indicator to false.
    /**
     * @param k the keyboard.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // pausing the game.
        d.drawText(10, d.getHeight() / 2,
                "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}