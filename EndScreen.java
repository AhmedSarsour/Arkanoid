import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;

//The EndScreen will be shown when the player either loses all the
//lives or completes the last level of the game.
/**
 * EndScreen.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class EndScreen implements Animation {
    private SpriteCollection theGameSprites;
    private GameLevel gamer;
    private boolean setAndGo;
    private GUI gui;

    // Constructor of the EndScreen, gets the shape of the level.
    /**
     * @param gameScreen of the level.
     */
    public EndScreen(SpriteCollection gameScreen) {
        this.theGameSprites = gameScreen;
        this.setAndGo = true;
    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        KeyboardSensor keyboard = this.gui.getKeyboardSensor();
        Sleeper sleeper = new Sleeper();
        while (true) {
            // drawing the current state of the level.
            d = gui.getDrawSurface();
            this.gamer.drawBorders(d);
            this.gamer.drawDecoration(d);
            if (this.gamer.getLives() == 0) {
                // the gamer lost all his lives, displaying a LOSE phrase.
                d.setColor(java.awt.Color.yellow);
                d.drawText(d.getWidth() / 3, d.getHeight() / 2,
                        "Game Over. Your score is "
                                + this.gamer.getScore().getScoreVal(),
                        32);
            } else if (this.gamer
                    .getRemainedBlocksNumberToRemoveToWin() <= 0) {
                // the gamer destroyed all the blocks, displaying a WIN phrase.
                d.setColor(java.awt.Color.yellow);
                d.drawText(d.getWidth() / 3, d.getHeight() / 2,
                        "You Win! Your score is "
                                + this.gamer.getScore().getScoreVal(),
                        32);
            }
            // if the space key is pressed, ending the display of the endScreen.
            if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.setAndGo = false;
                break;
            }
            // drawing the current state of the level.
            this.theGameSprites.drawAllOn(d);
            this.gui.show(d);
            sleeper.sleepFor(50);
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.setAndGo;
    }

    // getting the level's state and gui in order to show the end screen while
    // showing it.
    /**
     * @param theGameScr of the game's current level.
     * @param theGui which we work upon.
     */
    public void getTheGameScreen(GameLevel theGameScr, GUI theGui) {
        this.gamer = theGameScr;
        this.gui = theGui;
    }
}