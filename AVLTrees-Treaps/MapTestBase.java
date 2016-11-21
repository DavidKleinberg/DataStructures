//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public abstract class MapTestBase {
	private OrderedMap<String, String> map;

	protected abstract OrderedMap<String, String> createMap();

    @Before
    public void setupMap() {
        map = this.createMap();
    }

	@Test
    public void newMapEmpty() {
        assertEquals("{}", map.toString());
    }

    @Test
    public void newMapSize() {
    	int c = 0;
        for (String i : map) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void testNotEmpty() {
    	map.insert("A", "A");
        map.insert("B", "B");
        map.insert("C", "C");
        map.insert("D", "D");
        map.insert("E", "E");
    	int c = 0;
    	String string = "";
        for (String s : map) {
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
        map.insert("A", "A");
        map.insert("B", "B");
        map.insert("C", "C");
        map.insert("D", "D");
        map.insert("E", "E");
        Iterator<String> iterator = map.iterator();

        int c = 0;
        while (iterator.hasNext()) {
            c++;
        }

        assertEquals(5, c);
    }

    @Test
    public void hasNext() {
        Iterator<String> iterator = map.iterator();

        map.insert("A", "A");

        assertTrue(iterator.hasNext());
    }

    @Test
    public void next() {
        Iterator<String> iterator = map.iterator();

        map.insert("A", "A");

        assertEquals(iterator.next(), "{A: A}");
    }

/**
Insert
*/
    @Test
    public void insert() {
    	map.insert("A","A");
    	
        assertEquals("{A: A}", map.toString());
    }

/**
Remove
*/
    @Test
    public void remove() {
    	map.insert("A", "A");
        map.insert("B", "B");
    	map.remove("B");
        assertEquals("{A: A}", map.toString());
    }
/**
Has
*/
    @Test
    public void doesHave() {
    	map.insert("A","A");
        assertTrue(map.has("A"));
    }

    @Test
    public void doesNotHave() {
    	assertFalse(map.has("A"));
    }	
}