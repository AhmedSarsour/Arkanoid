/**
 * BallRemover.
 * @author ahmed.
 *         since 20.6.2017.
 */

public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    // the constructor of the ballRemover block.
    /**
     * BallGenerator constructor.
     * @param game the game.
     * @param removedBlocks count which can be removed.
     */
    public BallRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = game.getRemainedBallsNum();
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //the ball is removed when there is a hit event.
        this.game.removeSprite(hitter);
        this.remainingBalls.decrease(1);
        this.game.setNewBallsList(this.remainingBalls.getValue());
    }

    // getting the remaining balls number.
    /**
     * getRemainingBalls function.
     * @return remainingBalls number.
     */
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }
}
