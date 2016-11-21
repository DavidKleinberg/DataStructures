//David Kleinberg
//dkleinb1@jhu.edu

/** Counter that lets you initialize a value and increment. */
public class FlexibleCounter implements ResetableCounter {

    /** Stores the value of the counter. */
    private int value;

    /** Stores the value of the counter. */
    private int increment;

    /** Stores the value of the counter. */
    private int startVal;

    /** Initialize a FlexibleCounter.
    * @param start the value to which the counter is initialized
    * @param inc the initial times to increment */
    public FlexibleCounter(int start, int inc) {
        this.startVal = start;
        this.increment = inc;
        this.value = this.startVal;
    }

    /** Current value of this counter.
     * @return the value of the counter */
    public int value() {
        return this.value;
    }

    /** Increment this counter. */
    public void up() {
        this.value += this.increment;
    }

    /** Decrement this counter. */
    public void down() {
        this.value -= this.increment;
    }

    /** Reset this counter. */
    public void reset() {
        this.value = this.startVal;
    }

    /** Main calls assertions.
     * @param args */
    public static void main(String[] args) {
        Counter c = new FlexibleCounter(2, 5);
    assert c.value() == 2;
        c.up();
    assert c.value() == 7;
        c.down();
assert c.value() == 2;        c.down();        c.up();        c.up();        c.up();
assert c.value() == 12;
    }
}
