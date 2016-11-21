//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public abstract class SetTestBase {
	private Set<String> set;

	protected abstract Set<String> createSet();

    @Before
    public void setupSet() {
        set = this.createSet();
    }

	@Test
    public void newSetEmpty() {
    	String string = "";
    	for (String s : set) {
    		string += s;
    	}
        assertEquals("", string);
    }

    @Test
    public void newSetSize() {
    	int c = 0;
        for (String i : set) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void testNotEmpty() {
    	set.insert("A");
        set.insert("B");
        set.insert("C");
        set.insert("D");
        set.insert("E");
    	int c = 0;
    	String string = "";
        for (String s : set) {
            c++;
            string += s;
        }
        assertTrue(c > 0);
    }

/**
SetIterators
*/

	@Test
    public void iterator() {
        set.insert("A");
        set.insert("B");
        set.insert("C");
        set.insert("D");
        set.insert("E");
        Iterator<String> iterator = set.iterator();

        int c = 0;
        while (iterator.hasNext()) {
            c++;
        }

        assertEquals(5, c);
    }

    @Test
    public void hasNext() {
        Iterator<String> iterator = set.iterator();

        set.insert("TEST");

        assertTrue(iterator.hasNext());
    }

    @Test
    public void next() {
        Iterator<String> iterator = set.iterator();

        set.insert("TEST");

        assertEquals(iterator.next(), "TEST");
    }

    @Test(expected=UnsupportedOperationException.class)
    public void iteratorRemove() {
        Iterator<String> iterator = set.iterator();

        iterator.remove();
    }
/**
Insert
*/
    @Test
    public void insert() {
    	set.insert("TEST");
    	String string = "";
        for (String s : set) {
            string += s;
        }
        assertEquals("TEST", string);
    }

/**
Remove
*/
    @Test
    public void remove() {
    	set.insert("TEST");
    	set.remove("TEST");
    	String string = "";
    	for (String s : set) {
    		string += s;
    	}
        assertEquals("", string);
    }
/**
Has
*/
    @Test
    public void doesHave() {
    	set.insert("TEST");
        assertTrue(set.has("TEST"));
    }

    @Test
    public void doesNotHave() {
    	assertFalse(set.has("TEST"));
    }	
}