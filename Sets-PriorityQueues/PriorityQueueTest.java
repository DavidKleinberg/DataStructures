//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PriorityQueueTest {
	private BinaryHeapPriorityQueue<String> queue = new BinaryHeapPriorityQueue<String>();

	@Test
    public void newQueueEmpty() {
        queue = new BinaryHeapPriorityQueue<String>();
    	assertTrue(queue.empty());
    }

    @Test
    public void queueNotEmpty() {
        queue = new BinaryHeapPriorityQueue<String>();
        queue.insert("TEST");
        assertFalse(queue.empty());
    }

    @Test
    public void insertAndTop() {
        queue = new BinaryHeapPriorityQueue<String>();
    	queue.insert("TEST");

        assertEquals("TEST", queue.top());
    }

    @Test
    public void priorityOrdering() {
        queue = new BinaryHeapPriorityQueue<String>();
        for (int i = 0; i < 5; i++) {
            queue.insert(Integer.toString(i));
        }
        for (int i = 9; i >= 5; i--) {
            queue.insert(Integer.toString(i));
        }

        String string = "";
        for (int i = 0; i < 10; i++) {
            string += queue.top();
            queue.remove();
        }

        assertEquals("0123456798", string);
    }

    @Test
    public void remove() {
        queue = new BinaryHeapPriorityQueue<String>();
        queue.insert("TEST");
        queue.remove();
        assertTrue(queue.empty());
    }   

    @Test(expected=EmptyException.class)
    public void removeEmpty() {
        queue = new BinaryHeapPriorityQueue<String>();
    	queue.remove();
    }

    @Test(expected=EmptyException.class)
    public void topEmpty() {
        queue = new BinaryHeapPriorityQueue<String>();
        queue.top();
    }   
}