//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Test;
import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test the axioms specific to the ArrayDequeue.
 * @author David
 *
 */
public class TestArrayDequeue {

	static ArrayDequeue<Integer> arrayDeck;
	
	@BeforeClass
	public static void setupDequeue() {
		arrayDeck = new ArrayDequeue<Integer>();
	}
/**
	AXIOMS
*/

	@Test
	public void testInitialEmpty() {
		arrayDeck = new ArrayDequeue<Integer>();
		assertTrue(arrayDeck.empty());
	}

	@Test
	public void testInitialString() {
		arrayDeck = new ArrayDequeue<Integer>();
		assertEquals("[]", arrayDeck.toString());
	}

	@Test
	public void testNotEmpty() {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.insertFront(1);
		assertFalse(arrayDeck.empty());
	}

	@Test
	public void testInitialLength() {
		arrayDeck = new ArrayDequeue<Integer>();
		assertEquals(0, arrayDeck.length());
	}

	@Test
	public void testIncreaseLength() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 10; i++) {
			arrayDeck.insertFront(1);
		}
		assertEquals(10, arrayDeck.length());
	}

	@Test
	public void testDecreaseLength() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 10; i++) {
			arrayDeck.insertFront(1);
		}
		//the for loop above will create a Dequeue
		//of length 10, as shown in testIncreaseLength()
		arrayDeck.removeBack();
		assertEquals(9, arrayDeck.length());
	}

	@Test
	public void testFirstFront() {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.insertFront(1);
		assertEquals(1, (int) arrayDeck.front());
		assertTrue(arrayDeck.front() == arrayDeck.back());
	}

	@Test
	public void testFirstBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.insertBack(2);
		assertEquals(2, (int) arrayDeck.back());
		assertTrue(arrayDeck.front() == arrayDeck.back());
	}

	@Test
	public void testFrontBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.insertBack(1);
		arrayDeck.insertBack(2);
		assertTrue(arrayDeck.front() != arrayDeck.back());
	}

	@Test
	public void testInsertFront() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
		}
		assertEquals("[4, 3, 2, 1, 0]", arrayDeck.toString());
	}

	@Test
	public void testInsertBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertBack(i);
		}
		assertEquals("[0, 1, 2, 3, 4]", arrayDeck.toString());
	}

	@Test
	public void testRemoveFront() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
		}
		assertEquals(5, arrayDeck.length());
		arrayDeck.removeFront();
		assertEquals("[3, 2, 1, 0]", arrayDeck.toString());
		assertEquals(4, arrayDeck.length());
	}

	@Test
	public void testRemoveBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
		}
		assertEquals(5, arrayDeck.length());
		arrayDeck.removeBack();
		assertEquals("[4, 3, 2, 1]", arrayDeck.toString());
		assertEquals(4, arrayDeck.length());
	}

	@Test
	public void testFront() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
		}
		assertEquals(4, (int) arrayDeck.front());
	}

	@Test
	public void testBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
		}
		assertEquals(0, (int) arrayDeck.back());
	}

	@Test
	public void makeEmpty() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
		}
		assertTrue(arrayDeck.length() > 0);
		for (int i = 0; i < 5; i++) {
			arrayDeck.removeBack();
		}
		assertTrue(arrayDeck.empty());
	}

	@Test
	public void insertFrontBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		assertEquals("[]", arrayDeck.toString());
		for (int i = 0; i < 5; i++) {
			arrayDeck.insertFront(i);
			arrayDeck.insertBack(i);
		}
		assertEquals("[4, 3, 2, 1, 0, 0, 1, 2, 3, 4]", arrayDeck.toString());
		//make sure these have actually been added
		assertEquals(10, arrayDeck.length());
	}

	@Test
	public void removeFrontBack() {
		arrayDeck = new ArrayDequeue<Integer>();
		for (int i = 0; i < 10; i++) {
			arrayDeck.insertFront(i);
		}
		arrayDeck.removeFront();
		assertEquals("[8, 7, 6, 5, 4, 3, 2, 1, 0]", arrayDeck.toString());
		arrayDeck.removeBack();
		assertEquals("[8, 7, 6, 5, 4, 3, 2, 1]", arrayDeck.toString());
		//make sure these have actually been removed
		assertEquals(8, arrayDeck.length());
	}

/**
	PRECONDITIONS
*/
	@Test(expected = EmptyException.class)
	public void testFrontPre() throws EmptyException {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.front();
	}

	@Test(expected = EmptyException.class)
	public void testBackPre() throws EmptyException {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.back();
	}

	@Test(expected = EmptyException.class)
	public void testRemoveFrontPre() throws EmptyException {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.removeFront();
	}

	@Test(expected = EmptyException.class)
	public void testRemoveBackPre() throws EmptyException {
		arrayDeck = new ArrayDequeue<Integer>();
		arrayDeck.removeBack();

	}
}