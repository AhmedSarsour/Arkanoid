import java.util.List;

import biuoop.DialogManager;
import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * GameFlow.
 * @author ahmed.
 * @since 20.05.2017.
 */

public class GameFlow {
    private AnimationRunner run;
    private int lives;
    private ScoreTrackingListener score;
    private GUI gui;
    private HighScoresTable highScoresList;

    // GameFlow constructor, setting the gui which we work upon, the animation
    // runner, the lives and lastly the score.
    /**
     * GameFlow Constructor.
     * @param theGui GUI.
     * @param theAnimated which is animationrunner.
     * @param scoresList the High scores table.
     */
    public GameFlow(GUI theGui, AnimationRunner theAnimated,
            HighScoresTable scoresList) {
        this.gui = theGui;
        this.run = theAnimated;
        this.lives = 7;
        this.score = new ScoreTrackingListener(new Counter(0));
        this.highScoresList = scoresList;
    }

    // runs a list of given-levels.
    /**
     * runLevels function.
     * @param levels of the game.
     */
    public void runLevels(List<LevelInformation> levels) {
        // getting the last level index, and setting a counter in order to
        // display the end screen only when finishing the last level or losing
        // all the lives.
        int lastLevelIndex = levels.size();
        int counter = 0;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.run,
                    this.lives, this.score, this.gui);
            level.initialize();
            counter++;
            while (level.getLives() > 0 && level
                    .getRemainedBlocksNumberToRemoveToWin() > 0) {
                level.playOneTurn();
                // adjusting the lives, in case a live was lost in a past-level.
                this.lives = level.getLives();
                // checking if the player reached the last level or not.
                if (counter == lastLevelIndex) {
                    // the player reached the last level, checking if he
                    // finishes the last level or not.
                    if (level
                            .getRemainedBlocksNumberToRemoveToWin() <= 0) {
                        // he managed to finish the last level, displaying the
                        // end screen.
                        EndScreen end = new EndScreen(
                                level.getSprites());
                        // giving the end-screen the current state of the level.
                        end.getTheGameScreen(level, this.gui);
                        DrawSurface d = this.gui.getDrawSurface();
                        // after displaying the end-screen, pressing space-key
                        // to end the animation.
                        if (!end.shouldStop()) {
                            end.doOneFrame(d, 1 / 60);
                        }
                        this.highScoresList.add(new ScoreInfo("ahmed",
                                this.score.getScoreVal()));
                        break;
                    }
                }
                // checking whether the player lost all the lives or not.
                if (level.getLives() <= 0) {
                    // the player lost all his lives, displaying the end screen.
                    EndScreen end = new EndScreen(level.getSprites());
                    end.getTheGameScreen(level, this.gui);
                    DrawSurface d = this.gui.getDrawSurface();
                    if (!end.shouldStop()) {
                        end.doOneFrame(d, 1 / 60);
                    }
                    // if (this.score.getScoreVal() >=
                    // this.highScoresList.getHighScores().get(this.highScoresList.size()
                    // - 1).getScore()) {
                    DialogManager dialog = this.gui
                            .getDialogManager();
                    String name = dialog.showQuestionDialog("Name",
                            "What is your name?", "");
                    this.highScoresList.add(new ScoreInfo(name,
                            this.score.getScoreVal()));
                    // display the scores unless the player presses the key
                    // "space"
                    HighScoresAnimation showScores = new HighScoresAnimation(
                            this.highScoresList, "space", this.gui);
                    if (!showScores.shouldStop()) {
                        showScores.doOneFrame(d, 1 / 60);
                    }
                    // this.gui.close();
                    break;
                }
            }
        }
    }

    // returns the high scores list;
    /**
     * @return highSocresList of the scores.
     */
    public HighScoresTable getScoresList() {
        return this.highScoresList;
    }
}