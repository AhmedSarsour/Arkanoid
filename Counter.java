/**
 * Counter.
 * @author ahmed.
 * @since 20.05.2017.
 */
public class Counter {
    private int count;

    // the Counter's constructor.
    /**
     * constructor.
     * @param initialCount number.
     */
    public Counter(int initialCount) {
        this.count = initialCount;
    }

    // add number to current count.
    /**
     * @param number to add to counter.
     */
    public void increase(int number) {
        this.count += number;
    }

    // subtract number from current count.
    /**
     * @param number to subtract from counter.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    // get current count.
    /**
     * @return count the value.
     */
    public int getValue() {
        return this.count;
    }
}