//David Kleinberg
//dkleinb1@jhu.edu

/** The essence of any counter. */
public interface Counter {

    /** Current value of this counter.
     * @return the value of the counter.*/
    int value();

    /** Increment this counter. */
    void up();

    /** Decrement this counter. */
    void down();
}
