//David Kleinberg
//dkleinb1@jhu.edu

/** counts by squaring or taking square root. */
public class SquareCounter implements ResetableCounter {

    private int value = 2;

    /** Current value of this counter.
    * @return value */
    public int value() {
        return this.value;
    }

    /** Increment this counter. */
    public void up() {
        double tempVal = Math.pow(this.value, 2);
        this.value = (int) tempVal;
    }

    /** Decrement this counter. */
    public void down() {
        double tempVal = Math.sqrt(this.value);
        if (tempVal < 2) {
            this.reset();
        } else {
            this.value = (int) tempVal;
        }
    }

    /** Resets the counter. */
    public void reset() {
        this.value = 2;
    }

    /** Main calls assertions.
     * @param args */
    public static void main(String[] args) {
        Counter c = new SquareCounter();
    assert c.value() == 2;
        c.up();
    assert c.value() == 4;
        c.down();assert c.value() == 2;        c.down();        c.up();        c.up();        c.up();
assert c.value() == 256;
    }
}
