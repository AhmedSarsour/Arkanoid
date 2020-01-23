import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner.
 * @author ahmed.
 *         since 20.6.2017.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * AnimationRunner Constructor.
     * @param theGui which we work on.
     */
    public AnimationRunner(GUI theGui) {
        this.gui = theGui;
    }
    // runs the animated object. in our case, the game or the time countdown.
    /**
     * run function.
     * @param animation the object.
     */
    public void run(Animation animation) {
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            //functioning the animated object.
            animation.doOneFrame(d, 1 / 60);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame
                    - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}