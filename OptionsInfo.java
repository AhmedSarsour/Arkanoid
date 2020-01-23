/**
 * OptionsInfo.
 * @author ahmed.
 * @param <T> the type.
 *         since 14.06.2017.
 */
public class OptionsInfo<T> {
    private String keyToPress, phrase;
    private T optStr;

    /**
     * OptionsInfo constructor.
     * @param pressKey to click.
     * @param thePhrase to work upon.
     * @param returnOpt the show on menu.
     */
    public OptionsInfo(String pressKey, String thePhrase,
            T returnOpt) {
        this.keyToPress = pressKey;
        this.phrase = thePhrase;
        this.optStr = returnOpt;
    }

    // getter for the press - key option.
    /**
     * @return keyToPress the key.
     */
    public String getPressKey() {
        return this.keyToPress;
    }

    // getter for the string-to-be-shown on screen.
    /**
     * @return phrase to print on screen.
     */
    public String getPhrase() {
        return this.phrase;
    }

    // getter for the pressed Option string.
    /**
     * @return optStr the String of the selected option.
     */
    public T getOptStr() {
        return this.optStr;
    }
}
