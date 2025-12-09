// 318844677 Netanel Berkovits

/**
 * Name: GameElements.Counter
 * Description: A GameElements.Counter Object.
 */
public class Counter {

    private int value;

    /**
     * Name: GameElements.Counter
     * Description: A constructor for GameElements.Counter.
     *
     * @param value the starting number of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Name: GameElements.Counter
     * Description: A constructor for GameElements.Counter.
     */
    public Counter() {
        this(0);
    }

    // add number to current count.

    /**
     * Name: increase
     * Description: Increase hte counter by a number.
     *
     * @param number the number we want to increase the counter by.
     */
    public void increase(int number) {
        this.value += number;
    }

    // subtract number from current count.

    /**
     * Name: decrease
     * Description: Decrease hte counter by a number.
     *
     * @param number the number we want to decrease the counter by.
     */
    public void decrease(int number) {
        this.value -= number;
    }

    // get current count.

    /**
     * Name: getValue
     * Description: Getter for this counter value.
     *
     * @return return this counter value.
     */
    public int getValue() {
        return this.value;
    }
}