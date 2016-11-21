//David Kleinberg
//dkleinb1@jhu.edu

/** Basic counter that increments by 1. */
public final class BasicCounter implements ResetableCounter {

    private int value;

    /** Current value of this counter.
     * @return the value of the counter. */
    public int value() {
        return this.value;
    }

    /** Increment this counter. */
    public void up() {
        this.value++;
    }

    /** Decrement this counter. */
    public void down() {
        this.value--;
    }

    /** Reset this counter. */
    public void reset() {
        this.value = 0;
    }

    /** Main that uses assertions.
     * @param args */
    public static void main(String[] args) {
        Counter c = new BasicCounter();
    assert c.value() == 0;
        c.up();
    assert c.value() == 1;
        c.down();
assert c.value() == 0;
        c.down();
        c.up();
        c.up();
        c.up();
assert c.value() == 2;
    }
}
