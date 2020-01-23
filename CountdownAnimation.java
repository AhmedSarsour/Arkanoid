import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

//The CountdownAnimation will display the given gameScreen,
//for numOfSeconds seconds, and on top of them it will show
//a countdown from countFrom back to 1, where each number will
//appear on the screen for (numOfSeconds / countFrom) seconds, before
//it is replaced with the next one.

/**
 * CountdownAnimation.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class CountdownAnimation implements Animation {
    private double numSeconds;
    private int countingFrom;
    private SpriteCollection theGameSprites;
    private GameLevel gamer;
    private boolean setAndGo;
    private GUI gui;

    // CountdownAnimation constructor, getting the number of seconds and
    // counting from which number and the sprites of the game's level.
    /**
     * @param numOfSeconds to display the count.
     * @param countFrom number.
     * @param gameScreen of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
            SpriteCollection gameScreen) {
        this.numSeconds = numOfSeconds;
        this.countingFrom = countFrom;
        this.theGameSprites = gameScreen;
        this.setAndGo = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        // converting the time into seconds.
        double time = 1000 * this.numSeconds / this.countingFrom;
        Sleeper sleeper = new Sleeper();
        while (this.countingFrom >= 0) {
            d = gui.getDrawSurface();
            // drawing the decorations and borders of the game's level.
            this.gamer.drawBorders(d);
            this.gamer.drawDecoration(d);
            if (this.countingFrom > 0) {
                // drawing the count down.
                d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                        String.valueOf(this.countingFrom) + "...",
                        32);
            }
            // stopping the count-down in case it reached 0, and displaying a
            // "GO...".
            if (this.countingFrom <= 0) {
                d.drawText(d.getWidth() / 2, d.getHeight() / 2,
                        "GO...", 32);
                this.setAndGo = false;
            }
            // reducing the count.
            this.countingFrom--;
            // drawing the sprites of the game.
            this.theGameSprites.drawAllOn(d);
            this.gui.show(d);
            sleeper.sleepFor((int) time);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.setAndGo;
    }

    // getting the gui and the game's level for this class in order to show draw
    // the count down properly on it.
    /**
     * getTheGameScreen function.
     * @param theGameScr of the level.
     * @param theGui which we are working on.
     */
    public void getTheGameScreen(GameLevel theGameScr, GUI theGui) {
        this.gamer = theGameScr;
        this.gui = theGui;
    }
}