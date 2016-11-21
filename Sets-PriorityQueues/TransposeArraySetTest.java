//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class TransposeArraySetTest extends SetTestBase {
    
    private Set<String> set;

    @Override
    protected Set<String> createSet() {
        return new TransposeArraySet<>();
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

        assertEquals("ABCDE", string);
    }

    @Test
    public void hasOrder() {
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

        assertEquals("ACBDE", string);
    }

}
