import biuoop.DrawSurface;

/**
 * Animation interface.
 * @author ahmed.
 *         since 20.6.2017.
 */
public interface Animation {
    // the whole animation occurs in this function, moving the balls, paddle and
    // blocks removing..etc.
    /**
     * @param d the drawSurface.
     * @param dt the speed indicator.
     */
    void doOneFrame(DrawSurface d, double dt);

    // returns if the animation should be stopped or not.
    /**
     * @return boolean.
     */
    boolean shouldStop();
}