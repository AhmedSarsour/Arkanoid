//a BlockRemover is in charge of removing blocks from the game, as well as keeping count
//of the number of blocks that remain.
/**
 * BlockRemover.
 * @author ahmed.
 * @since 20.05.2017.
 */

public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private Counter removedBlock;

    // Constructor that gets the game and the number of blocks that can be
    // removed.
    /**
     * @param game the one that is functioning.
     * @param removedBlocks count that can be removed.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        // this.remainingBlocks = game.getRemainedBlocksNum();
        this.remainingBlocks = new Counter(
                game.getRemainedBlocksNumberToRemoveToWin());
        this.removedBlock = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // calling the ScoreTrackingListener score in order to add score -
        // points.
        this.game.getScore().hitEvent(beingHit, hitter);
        if (beingHit.getHitPoints() == 1) {
            // the hit points of the block is 1 so after the hit event it will
            // be 0.
            // meaning that it should be removed.
            // removing the block from the game and the collidables list.
            this.game.removeCollidable(beingHit);
            this.game.removeSprite(beingHit);
            // beingHit.removeHitListener(beingHit.getHitListener());
            // decreasing the number of remaining blocks.
            this.remainingBlocks.decrease(1);
            // adjusting the numbero of the blocks in the game.
            this.game.setRemainedBlocksNumberToRemoveToWin(
                    this.remainingBlocks.getValue());
            // adjusting the spots of the balls in the sprite collection.
            this.game.setBallsSpot();
            if (this.remainingBlocks.getValue() <= 0) {
                // adding the score-points when clearing the level.
                this.game.getScore().levelClear();
            }
        }
    }

    // getting the number of the remaining blocks in the game.
    /**
     * getRemainingBlocks function.
     * @return remainingBlocks number.
     */
    public int getRemainingBlocks() {
        return this.remainingBlocks.getValue();
    }
}