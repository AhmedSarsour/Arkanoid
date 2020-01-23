/**
 * hitNotifier interface.
 * @author ahmed.
 *         since 20.5.2017.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * @param hl the HitListener.
     */
    void addHitListener(HitListener hl);

    // Remove hl from the list of listeners to hit events.
    /**
     * @param hl the HitListener.
     */
    void removeHitListener(HitListener hl);
}