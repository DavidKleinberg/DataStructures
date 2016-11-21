import org.junit.Test;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Test the axioms specific to the StatableArray.
 * @author David
 *
 */
public class StatableArrayTest {

    static StatableArray<String> array;

    @Test
    public void CountersInitialized() throws LengthException, IndexException {
        array = new StatableArray<String>(100, "a");
        assertNotNull(array.numberOfReads());
        assertEquals(0, array.numberOfReads());
        assertNotNull(array.numberOfWrites());
        assertEquals(0, array.numberOfWrites());
    }

    @Test
    public void readCounter() throws LengthException, IndexException {
        array = new StatableArray<String>(100, "b");

        //check that the numberOfReads isn't affected by writing methods
        for (int i = 0; i < 100; i++) {
            array.put(i, "c");
        }

        for (int i = 0; i < array.length(); i++) {
            array.get(i);
        }

        //check that both get and length change the number of reads
        //length is being called length+1 times in the for loop
    	assertEquals(201, array.numberOfReads());
    }

    @Test
    public void writeCounter() throws LengthException, IndexException {
        array = new StatableArray<String>(100, "d");

        //check that the numberOfWrites isn't affected by reading methods
        for (int i = 0; i < array.length(); i++) {
            array.get(i);
        }

        for (int i = 0; i < array.length(); i++) {
            array.put(i, "e");
        }

        assertEquals(array.length(), array.numberOfWrites());
    }

    @Test
    public void resetStats() throws LengthException, IndexException {
        array = new StatableArray<String>(100, "f");

        for (int i = 0; i < array.length(); i++) {
            array.get(i);
        }

        for (int i = 0; i < array.length(); i++) {
            array.put(i, "g");
        }

        assertTrue(array.numberOfReads() > 0);
        assertTrue(array.numberOfWrites() > 0);

        array.resetStatistics();
    	assertEquals(0, array.numberOfReads());
    	assertEquals(0, array.numberOfWrites());
    }
}

