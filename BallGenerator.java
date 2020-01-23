import java.util.Random;

/**
 * BallGenerator.
 * @author ahmed.
 *         since 20.6.2017.
 */
public class BallGenerator implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    // the constructor of the ballGenerator block.
    /**
     * BallGenerator constructor.
     * @param game the game.
     * @param removedBlocks which can be removed.
     */
    public BallGenerator(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBalls = game.getRemainedBallsNum();
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // this.game.removeSprite(hitter);
        Random rand = new Random();
        Sprite ballnew = new Ball(
                new Point(
                        beingHit.getlSide() + rand
                                .nextInt((int) beingHit.getWidth()),
                        beingHit.getuSide()),
                3, java.awt.Color.WHITE);
        ((Ball) ballnew).getFrameSize(20, 40, 800 - 20, 600 - 20);
        // getting a velocity.
        Velocity v2 = Velocity.fromAngleAndSpeed(
                rand.nextInt(160) - 80, rand.nextInt(3) + 2);
        // hitter.getVelocity().getDx());
        // adding the velocity to the ball.
        ((Ball) ballnew).setVelocity(v2);
        // adding the second ball to the game.
        ((Ball) ballnew).addToGame(this.game);
        this.game.setNewAddBallList();
        this.remainingBalls.increase(1);

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
