//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class AVLMapTest extends MapTestBase {
    
    private OrderedMap<String, String> map;

    @Override
    protected OrderedMap<String, String> createMap() {
        return new AVLTreeMap<>();
    }

}
