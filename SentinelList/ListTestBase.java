//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the List interface.
 *
 * The tests defined here apply to all implementations of the List
 * interface. However, they cannot be run directly as we don't know
 * which implementation to test or how to create an instance of it.
 *
 * The solution is to define a "template method" called createList()
 * that subclasses of this test override. The NodeListTest.java class,
 * for example, creates a suitable NodeList instance to be tested.
 *
 * (We could use a JUnit feature called "parameterized tests" to do
 * the same thing, however that feature is a bit more complex to use
 * than we would like.)
 *
 * Note that we (somewhat arbitrarily) choose to test lists of strings.
 * We could have gone for lists of integers or lists of whatever, but
 * strings seem convenient in any case: You can pick strings in such a
 * way as to make your test cases more readable.
 */
public abstract class ListTestBase {
    private List<String> list;

    protected abstract List<String> createList();

    @Before
    public void setupListTests() {
        list = this.createList();
    }
/**
    Empty and Length Axioms
 */

    @Test
    public void newListEmpty() {
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertEquals("[]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void testNotEmpty() {
        list.insertFront("Test");
        assertFalse(list.empty());
    }

    @Test
    public void testLength() {
        assertEquals(0, list.length());
        list.insertFront("Test");
        assertEquals(1, list.length());
        list.removeFront();
        assertEquals(0, list.length());
    }

/**
    Front and Back Axioms
 */
    @Test
    public void frontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        Position<String> three = list.insertFront("Three");

        assertEquals("Three", list.front().get());
        assertEquals(three, list.front());
    }

    @Test
    public void backWorks() {
        Position<String> one = list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertEquals("One", list.back().get());
        assertEquals(one, list.back());
    }

    @Test
    public void newFrontEqualsBack() {
        list.insertFront("Test");
        assertTrue(list.first(list.back()));
    }

    @Test
    public void newBackEqualsFront() {
        list.insertFront("Test");
        assertTrue(list.last(list.front()));
    }

/**
    First and Last Axioms
 */
    @Test
    public void firstWorks() {
        Position<String> a = list.insertFront("Test");
        Position<String> b = list.insertBack("Test");
        
        assertTrue(list.first(a));
        assertFalse(list.first(b));
    }

    @Test
    public void lastWorks() {
        Position<String> a = list.insertFront("Test");
        Position<String> b = list.insertBack("Test");
        
        assertTrue(list.last(b));
        assertFalse(list.last(a));
    }

/**
    Insert Back, Front, and Reference Axioms
 */

    @Test
    public void insertFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Three, Two, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertBackWorks() {
        list.insertBack("One");
        list.insertBack("Two");
        list.insertBack("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[One, Two, Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    @Test
    public void insertFrontBackConsistent() {
        Position<String> f = list.insertFront("Front");
        assertEquals("Front", f.get());
        Position<String> b = list.insertBack("Back");
        assertEquals("Back", b.get());

        assertNotEquals(f, b);

        assertTrue(list.first(f));
        assertTrue(list.last(b));

        Position<String> x;

        x = list.front();
        assertEquals(f, x);

        x = list.back();
        assertEquals(b, x);
    }

    @Test
    public void insertBeforeReference() {
        Position<String> one = list.insertFront("One");
        Position<String> three = list.insertFront("Three");
        Position<String> four = list.insertFront("Four");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Four, Three, One]", list.toString());

        Position<String> two = list.insertBefore(one, "Two");

        assertFalse(list.empty());
        assertEquals(4, list.length());
        assertEquals("[Four, Three, Two, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(4, c);
        assertEquals(two, list.next(three));
    }

    @Test
    public void insertAfterReference() {
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        list.remove(two);

        assertFalse(list.empty());
        assertEquals(2, list.length());
        assertEquals("[Three, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(2, c);
    }

/**
    Nulls
 */

    @Test
    public void insertFrontNull() {
        Position<String> a = list.insertFront(null);

        assertEquals("[null]", list.toString());
    }

    @Test
    public void insertBackNull() {
        Position<String> a = list.insertBack(null);

        assertEquals("[null]", list.toString());
    }

    @Test
    public void iteratorReachesNull() {
        Position<String> a = list.insertFront(null);

        assertTrue(list.iterator().hasNext());
    }

/**
    Remove Front, Back, Reference Axioms
 */

    @Test
    public void removeFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeFront();
        list.removeFront();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    @Test
    public void removeBackWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeBack();
        list.removeBack();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    @Test
    public void removeReference() { 
        Position<String> one = list.insertFront("One");
        Position<String> two = list.insertFront("Two");
        Position<String> three = list.insertFront("Three");
        list.remove(two);

        assertFalse(list.empty());
        assertEquals(2, list.length());
        assertEquals("[Three, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(2, c);
    }

/**
    Iterators Axioms
 */
    @Test
    public void newListIteratorAxioms() {

        String string = "";
        int c = 0;
        for (String s : list) {
            string += s;
            c++;
        }
        assertEquals("", string);
        assertEquals(0, c);

        assertFalse(list.iterator().hasNext());
        assertFalse(list.forward().hasNext());
        assertFalse(list.backward().hasNext());
    }

    @Test
    public void testForwardIterator() {
        list.insertFront("A");
        list.insertFront("B");
        list.insertFront("C");
        list.insertFront("D");

        assertEquals(4, list.length());

        int c = 0;
        String string = "";
        for (String s: list) {
            string += s;
            c++;
        }

        assertEquals(4, c);
        assertEquals("DCBA", string);
        assertEquals("D", list.forward().next());
    }

    @Test
    public void testBackwardIterator() {
        list.insertFront("A");
        list.insertFront("B");
        list.insertFront("C");
        list.insertFront("D");

        assertEquals(4, list.length());

        int c = 0;
        String string = "";
        Iterator<String> iterator = list.backward();
        for (int i = 0; i < list.length(); i++) {
            string += iterator.next();
            c++;
        }

        assertEquals(4, c);
        assertEquals("ABCD", string);
        assertEquals("A", list.backward().next());
    }

    @Test
    public void testIterator() {
        list.insertFront("A");
        list.insertFront("B");
        list.insertFront("C");
        list.insertFront("D");

        assertEquals(4, list.length());

        int c = 0;
        String string = "";
        for (String s: list) {
            string += s;
            c++;
        }

        assertEquals(4, c);
        assertEquals("DCBA", string);
        assertEquals("D", list.iterator().next());
    }

/**
    Next and Previous Axioms
 */
    @Test
    public void testNext() {
        Position<String> a = list.insertFront("Test");
        Position<String> b = list.insertFront("Test");
        Position<String> c = list.insertFront("Test");

        assertEquals(b, list.next(c));
    }

    @Test
    public void testPrevious() {
        Position<String> a = list.insertFront("Test");
        Position<String> b = list.insertFront("Test");
        Position<String> c = list.insertFront("Test");

        assertEquals(b, list.previous(a));
    }

    //show that one node has references to both prev and next
    //(because it is a doubly linked list)
    @Test
    public void testNextPrevious() {
        Position<String> a = list.insertFront("Test");
        Position<String> b = list.insertFront("Test");
        Position<String> c = list.insertFront("Test");

        assertEquals(a, list.next(b));
        assertEquals(c, list.previous(b));
    }
/**
    Front and Back Preconditions
 */

    @Test(expected=EmptyException.class)
    public void newListNoFront() {
        Position<String> p = list.front();
    }

    @Test(expected=EmptyException.class)
    public void newListNoBack() {
        Position<String> p = list.back();
    }

    /**
        Remove (Front, Back, and at a Reference) Preconditions
     */

    @Test(expected=PositionException.class)
    public void removeNull() {
        list.remove(null);
    }

    @Test(expected=EmptyException.class)
    public void newListRemoveFront() {
        list.removeFront();
    }

    @Test(expected=EmptyException.class)
    public void newListRemoveBack() {
        list.removeBack();
    }

    @Test(expected=PositionException.class)
    public void removeInvalidReference() {
        List<String> otherList = this.createList();
        Position<String> p = otherList.insertFront("Test");
        list.remove(p);
    }

    @Test(expected=PositionException.class)
    public void removeAlreadyRemovedFront() {
        list.insertFront("a");
        Position<String> p = list.insertFront("Test");
        list.removeFront();
        list.remove(p);
    }

    @Test(expected=PositionException.class)
    public void removeAlreadyRemovedBack() {
        list.insertBack("a");
        Position<String> p = list.insertBack("Test");
        list.removeBack();
        list.remove(p);
    }

    @Test(expected=PositionException.class)
    public void removeAlreadyRemovedReference() {
        Position<String> p = list.insertFront("Test");
        list.remove(p);
        list.remove(p);
    }

    /**
        InsertBefore Preconditions
     */

    @Test(expected=PositionException.class)
    public void insertBeforeInvalidReference() {
        List<String> otherList = this.createList();
        Position<String> p = otherList.insertFront("Test");
        list.insertBefore(p, "Test");
    }

    @Test(expected=PositionException.class)
    public void insertBeforeAlreadyRemovedFront() {
        Position<String> p = list.insertFront("Test");
        list.removeFront();
        list.insertBefore(p, "Test");
    }

    @Test(expected=PositionException.class)
    public void insertBeforeAlreadyRemovedBack() {
        Position<String> p = list.insertBack("Test");
        list.removeBack();
        list.insertBefore(p, "Test");
    }

    @Test(expected=PositionException.class)
    public void insertBeforeAlreadyRemovedReference() {
        Position<String> p = list.insertFront("Test");
        list.remove(p);
        list.insertBefore(p, "Test");
    }

    /**
        InsertAfter Preconditions
     */

    @Test(expected=PositionException.class)
    public void insertAfterInvalidReference() {
        List<String> otherList = this.createList();
        Position<String> p = otherList.insertFront("Test");
        list.insertAfter(p, "Test");
    }

    @Test(expected=PositionException.class)
    public void insertAfterAlreadyRemovedFront() {
        Position<String> p = list.insertFront("Test");
        list.removeFront();
        list.insertAfter(p, "Test");
    }

    @Test(expected=PositionException.class)
    public void insertAfterAlreadyRemovedBack() {
        Position<String> p = list.insertFront("Test");
        list.removeBack();
        list.insertAfter(p, "Test");
    }

    @Test(expected=PositionException.class)
    public void insertAfterAlreadyRemovedReference() {
        List<String> otherList = this.createList();
        Position<String> p = otherList.insertFront("Test");
        list.remove(p);
        list.insertAfter(p, "Test");
    }

    /**
        Iterator Preconditions
     */

        @Test(expected=NullPointerException.class)
        public void iteratorOutOfBounds() {
            assertFalse(list.iterator().hasNext());
            list.iterator().next();
        }

        @Test(expected=NullPointerException.class)
        public void forwardOutOfBounds() {
            assertFalse(list.forward().hasNext());
            list.forward().next();
        }

        @Test(expected=NullPointerException.class)
        public void backwardOutOfBounds() {
            assertFalse(list.backward().hasNext());
            list.backward().next();
        }

    /**
        First and Last Preconditions
     */

        //Even though the positions may be the same (hold equal values),
        //if they aren't in the same list, this function call must throw
        //a Position Exception
        @Test(expected=PositionException.class)
        public void firstInvalidReference() {
            Position<String> a = list.insertFront("Test");
            Position<String> b = list.insertBack("Test");
            Position<String> c = list.insertFront("Test");

            List<String> otherList = this.createList();
            Position<String> x = otherList.insertFront("Test");

            list.first(x);
        }

        @Test(expected=PositionException.class)
        public void lastInvalidReference() {
            Position<String> a = list.insertFront("Test");
            Position<String> b = list.insertBack("Test");
            Position<String> c = list.insertFront("Test");

            List<String> otherList = this.createList();
            Position<String> x = otherList.insertFront("Test");

            list.last(x);
        }

        //Though theses look a lot like the last two,
        //it enforces a different precondition:
        //that whenever the list is EMPTY, an exception will be thrown
        @Test(expected=PositionException.class)
        public void newListFirst() {
        Position<String> a = list.insertFront("Test");
        list.removeFront();
        assertTrue(list.empty());

        list.first(a); 
    }

        @Test(expected=PositionException.class)
        public void newListLast() {
        Position<String> a = list.insertFront("Test");
        list.removeFront();
        assertTrue(list.empty());

        list.last(a); 
    }

    /**
        Next and Previous Preconditions
     */
        @Test(expected=PositionException.class)
        public void emptyListNext() {
            Position<String> a = list.insertFront("Test");
            list.remove(a);

            assertTrue(list.empty());

            list.next(a);
        }

        @Test(expected=PositionException.class)
        public void endOfListNext() {
            Position<String> a = list.insertFront("Test");
            Position<String> b = list.insertFront("Test");
            Position<String> c = list.insertFront("Test");

            list.next(a);
        }

        @Test(expected=PositionException.class)
        public void emptyListPrevious() {
            Position<String> a = list.insertFront("Test");
            list.remove(a);
            
            assertTrue(list.empty());

            list.next(a);
        }

        @Test(expected=PositionException.class)
        public void beginningOfListPrev() {
            Position<String> a = list.insertFront("Test");
            Position<String> b = list.insertFront("Test");
            Position<String> c = list.insertFront("Test");

            list.previous(c);
        }

        @Test(expected=PositionException.class)
        public void invalidReferenceNext() {
            List<String> otherList = this.createList();
            Position<String> x = otherList.insertFront("Test");
            Position<String> a = list.insertFront("Test");
            Position<String> b = list.insertFront("Test");
            Position<String> c = list.insertFront("Test");

            list.next(x);
        }

        @Test(expected=PositionException.class)
        public void invalidReferencePrev() {
            List<String> otherList = this.createList();
            Position<String> x = otherList.insertFront("Test");
            Position<String> a = list.insertFront("Test");
            Position<String> b = list.insertFront("Test");
            Position<String> c = list.insertFront("Test");

            list.previous(x);
        }
}
