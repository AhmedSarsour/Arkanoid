/**
 * ShowHiScoresTask.
 * @author ahmed.
 * @since 04.06.2017.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runs;
    private Animation highScoresAnimations;

    /**
     * ShowHiScoresTask constructor.
     * @param runner the AnimationRunner.
     * @param highScoresAnimation the animation of scores.
     */
    public ShowHiScoresTask(AnimationRunner runner,
            Animation highScoresAnimation) {
        this.runs = runner;
        this.highScoresAnimations = highScoresAnimation;
    }

    /**
     * run the displaying of the scores.
     * @return null.
     */
    public Void run() {
        this.runs.run(this.highScoresAnimations);
        return null;
    }
}