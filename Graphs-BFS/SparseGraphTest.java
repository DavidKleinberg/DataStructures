//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class SparseGraphTest extends GraphTestBase {

	private Graph<String, Integer> graph;


	@Override
    protected Graph<String, Integer> createGraph() {

        return new SparseGraph<>();
    }

    @Test
    public void digraph() {
    	graph = new SparseGraph<String, Integer>();

    	Vertex<String> a = this.graph.insert("A");
    	Vertex<String> b = this.graph.insert("B");
    	Edge<Integer> c = this.graph.insert(a, b, 7);
    	
        String string = ("digraph {\n\t\"A\";\n\t\"B\";\n\t\"A\" -> \"B\" [label=\"7\"];\n" + "}");

        assertEquals(string, graph.toString());
    }
}