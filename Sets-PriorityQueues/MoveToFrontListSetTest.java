//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class MoveToFrontListSetTest extends SetTestBase {
    
    private Set<String> set;

    @Override
    protected Set<String> createSet() {
        return new MoveToFrontListSet<>();
    }

    @Test
    public void insertOrder() {
        set.insert("A");
        set.insert("B");
        set.insert("C");
        set.insert("D");
        set.insert("E");

        String string = "";
        for (String s : set) {
            string += set;
        }

        assertEquals("EDCBA", string);
    }

    @Test
    public void hasChangesOrder() {
        set.insert("A");
        set.insert("B");
        set.insert("C");
        set.insert("D");
        set.insert("E");

        String string = "";
        for (String s : set) {
            string += set;
        }

        set.has("C");

        assertEquals(string, "CEDBA");
    }

    @Test
    public void insertAlreadyThereOrder() {
        set.insert("A");
        set.insert("B");
        set.insert("C");
        set.insert("D");
        set.insert("E");

        set.insert("A");

        String string = "";
        for (String s : set) {
            string += set;
        }

        assertEquals(string, "AEDCB");
    }
}
