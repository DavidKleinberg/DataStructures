//David Kleinberg
//dkleinb1@jhu.edu

import java.util.ArrayList; // see note in main() below
import java.util.Iterator;

/**
    Simple polymorphic test framework for arrays.
*/
public final class PolyArray {
    private static final int LENGTH = 113;
    private static final int INITIAL = 7;

    private PolyArray() {}

    // methods for testing axioms go here:

    /** Asserts that the length of the newly created array is correct.
     *
     * @param a The array
     */
    private static void testNewLength(Array<Integer> a) {
        assert a.length() == LENGTH;
    }

    /** Asserts that get can be called to an "empty" array
     * Asserts that the put method is changing the initialized values
     * and that the get works on these values
     *
     * @param a The array
     */
    private static void testGetPut(Array<Integer> a) {
        for (int i = 0; i < LENGTH; i++) {
            assert a.get(i) == INITIAL;
        }
        for (int i = 0; i < LENGTH; i++) {
            a.put(i, 10);
        }
        for (int i = 0; i < LENGTH; i++) {
            assert a.get(i) == 10;
        }
    }

    /**
     * Tests that the iterator is on the correct Node
     * Checks that the iterator can pass through all the Nodes
     * @param a
     */
    private static void testIteratorAxi(Array<Integer> a) {
        Iterator<Integer> iterator = a.iterator();
        int numCells = 0;
        int current = 0;
        a.put(LENGTH - 1, 10);
        while (iterator.hasNext()) {
            current = iterator.next();
            numCells++;
        }
        assert numCells == LENGTH;
        assert current == 10;
    }

    // methods for testing preconditions go here:

    /**
     * Establishes that get cannot be called
     * for an index < 0
     * or for an index >= the array's length
     * @param a
     */
    private static void testGet(Array<Integer> a) {
        try {
            a.get(LENGTH);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            a.get(-1);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
    }

    /**
     * Establishes that the put cannot be called
     * for an index < 0
     * or for an index >= the array's length
     * @param a
     */
    private static void testPut(Array<Integer> a) {
        try {
            a.put(LENGTH, 0);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            a.put(-1, 0);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
    }

    /**
     * Establishes that the next cannot be called
     * for an index >= the array's length
     * also checks that the hasNext is working
     * @param a
     */
    private static void testIteratorPre(Array<Integer> a) {
        Iterator<Integer> iterator = a.iterator();
        try {
            while (iterator.hasNext()) {
                iterator.next();
            }
            iterator.next();
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        } catch (ArrayIndexOutOfBoundsException e) {
            // passed the test, nothing to do
        } catch (NullPointerException e) {
            // passed the test, nothing to do
        }
    }

    /**
     * Establishes that an array must have length > 0.
     */
    private static void testNewWrongLength() {
        try {
            Array<Integer> a = new SimpleArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
    }

    /**
        Run (mostly polymorphic) tests on various array implementations.

        @param args Command line arguments (ignored).
    */
    public static void main(String[] args) {
        // For various technical reasons, we cannot use a plain Java array here
        // like we did in PolyCount. Sorry.
        ArrayList<Array<Integer>> arrays = new ArrayList<>();
        arrays.add(new SimpleArray<Integer>(LENGTH, INITIAL));
        arrays.add(new ListArray<Integer>(LENGTH, INITIAL));
        arrays.add(new SparseArray<Integer>(LENGTH, INITIAL));

        // Test all the axioms. We can do that nicely in a loop. In the test
        // methods, keep in mind that you are handed the same object over and
        // over again!
        for (Array<Integer> a: arrays) {
            testNewLength(a);
            testGetPut(a);
            testIteratorAxi(a);
        }

        // Test all the preconditions. Sadly we have to code each one of these
        // out manually, not even Java's reflection API would help...
        for (Array<Integer> a: arrays) {
            testNewWrongLength();
            testIteratorPre(a);
            testPut(a);
            testGet(a);
        }
    }
}
