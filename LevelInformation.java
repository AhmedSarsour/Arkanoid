import java.util.List;

/**
 * LevelInformation.
 * @author ahmed.
 *         since 20.5.2017.
 */
public interface LevelInformation {
    // returns the number of the balls in the game.
    /**
     * @return numberofBalls in the level.
     */
    int numberOfBalls();

    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * @return ballVelocities at beginning.
     */
    List<Velocity> initialBallVelocities();

    // the speed of the paddle.
    /**
     * @return number which is the Paddle Step length.
     */
    int paddleSpeed();

    // the paddle's width.
    /**
     * @return padWidth the width.
     */
    int paddleWidth();

    // the level name will be displayed at the top of the screen.
    /**
     * @return levelName string.
     */
    String levelName();

    // Returns a sprite with the background of the level.
    /**
     * @return backgroundColor of level.
     */
    Sprite getBackground();

    // The Blocks that make up this level, each block contains
    // its size, color and location.
    /**
     * @return blocks of the level.
     */
    List<Block> blocks();

    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     * @return numberOfBlocksToRemove in order to win.
     */
    int numberOfBlocksToRemove();
}