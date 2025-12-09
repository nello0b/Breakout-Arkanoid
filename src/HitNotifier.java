// 318844677 Netanel Berkovits

/**
 * Name: HitListener.HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * Name: addHitListener
     * Description: Add hl as a listener to hit events.
     *
     * @param hl the HitListener.HitListener we want to add to listen to hit events.
     */
    void addHitListener(HitListener hl);

    /**
     * Name: removeHitListener
     * Description: Remove hl from the list of listeners to hit events.
     *
     * @param hl the HitListener.HitListener we want to remove from listening to hit events.
     */
    void removeHitListener(HitListener hl);
}