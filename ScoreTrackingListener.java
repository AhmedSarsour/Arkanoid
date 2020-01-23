/**
 * ScoreTrackingListener.
 * @author ahmed.
 * @since 20.05.2017.
 */

public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    // ScoreTrackingListener constructor which is composed of a counter.
    /**
     * @param scoreCounter the count.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        // 5 points are added for each hit.
        if (beingHit.getHitPoints() >= 1) {
            this.currentScore.increase(5);
            ;
        }
        // 10 points are added for destroying a block.
        if (beingHit.getHitPoints() == 1) {
            this.currentScore.increase(10);
        }
    }

    // getting the value of the score.
    /**
     * @return currentScore the value.
     */
    public int getScoreVal() {
        return this.currentScore.getValue();
    }

    // adding 100 points to the score in case the level was cleared.
    /**
     * LevelCleared function.
     */
    public void levelClear() {
        this.currentScore.increase(100);
    }
}