/**
 * @author ahmed.
 * @param <T> a type.
 * @since 15.6.2017
 */
public interface Menu<T> extends Animation {
    // adds a menu selection, the key to press in order to run the option and a
    // message to show what option that is.
    /**
     * @param key to press.
     * @param message to write.
     * @param returnVal of options.
     */
    void addSelection(String key, String message, T returnVal);

    // returns the status of the option.
    /**
     * getStatus function.
     * @return <T> the type of the option.
     */
    T getStatus();
}