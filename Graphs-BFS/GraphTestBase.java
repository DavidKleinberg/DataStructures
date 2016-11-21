//David Kleinberg
//dkleinb1@jhu.edu

import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public abstract class GraphTestBase {

    private Graph<String, Integer> graph;
    private Vertex<String> a;
    private Vertex<String> b;
    private Vertex<String> c;
    private Vertex<String> d;
    private Vertex<String> e;
    private Vertex<String> f;
    private Vertex<String> g;
    private Vertex<String> r;

    private Edge<Integer> h;
    private Edge<Integer> i;
    private Edge<Integer> j;
    private Edge<Integer> k;
    private Edge<Integer> l;
    private Edge<Integer> m;
    private Edge<Integer> n;
    private Edge<Integer> o;


	protected abstract Graph<String, Integer> createGraph();
    
    private void addVertices(Graph<String, Integer> graph) {
        this.a = graph.insert("A");
        this.b = graph.insert("B");
        this.c = graph.insert("C");
        this.d = graph.insert("D");
        this.e = graph.insert("E");
        this.f = graph.insert("F");
        this.g = graph.insert("G");
        this.r = graph.insert("REMOVABLE");
    }

    private void addEdges(Graph<String, Integer> graph) {
        this.h = graph.insert(a,b,1);
        this.i = graph.insert(a,c,2);
        this.j = graph.insert(b,d,3);
        this.k = graph.insert(b,e,4);
        this.l = graph.insert(b,f,5);
        this.m = graph.insert(c,e,6);
        this.n = graph.insert(c,d,7);
        this.o = graph.insert(c,f,8);
    }

    @Before
    public void setupGraphTests() {
        graph = this.createGraph();
    }

/**
    Empty and Size Axioms
 */

    @Test
    public void newGraphNoVertices() {
        int c = 0;
        for (Vertex<String> v : graph.vertices()) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void newGraphNoEdges() {
        int c = 0;
        for (Edge<Integer> e : graph.edges()) {
            c++;
        }
        assertEquals(0, c);
    }

    /**
     INSERT and CHANGE VERTEX AXIOMS and PRECONDITIONS
     */
    @Test
    public void insertVertex() {
        Vertex<String> v = graph.insert("TEST");
        
        for (Vertex<String> vertex : graph.vertices()) {
            assertEquals(vertex, v);
        }
    }

    @Test
    public void getVertex() {
    	Vertex<String> v = graph.insert("TEST");
        assertEquals("TEST", v.get());
    }

    @Test
    public void changeVertex() {
        Vertex<String> v = graph.insert("TEST");
        assertEquals("TEST", v.get());

        v.put("CHANGE");
        assertEquals("CHANGE", v.get());
    }

    /**
     INSERT EDGE AXIOM and PRECONDITIONS
     * @param from Vertex position where edge starts.
     * @param to Vertex position where edge ends.
     * @param e Element to insert.
     */
    public void insertEdgeAxiom() {
        addVertices(graph);

        Edge<Integer> e = graph.insert(this.a, this.b, 10);

        for (Edge<Integer> edge : graph.edges()) {
            assertEquals(edge, e);
        }
    }

    public void getEdge() {
        addVertices(graph);

        Edge<Integer> e = graph.insert(this.a, this.b, 10);
        assertEquals(10, (int)e.get());
    }

    public void changeEdge() {
        addVertices(graph);

        Edge<Integer> e = graph.insert(this.a, this.b, 10);
        assertEquals(10, (int)e.get());
        e.put(5);
        assertEquals(5, (int)e.get());
    }

    @Test(expected=PositionException.class)
    public void insertEdgeBadTo() {
        addVertices(graph);

        Graph<String, Integer> otherGraph = this.createGraph();

        Vertex<String> x = otherGraph.insert("TEST");
    	graph.insert(this.a, x, 10);
    }

    @Test(expected=PositionException.class)
    public void insertEdgeBadFrom() {
        addVertices(graph);

        Graph<String, Integer> otherGraph = this.createGraph();

        Vertex<String> x = otherGraph.insert("TEST");

        graph.insert(x, this.b, 10);
    }

    @Test(expected=InsertionException.class)
    public void insertSelfLoopEdge() {
        addVertices(graph);
    	graph.insert(this.a, this.a, 10);
    }

    @Test(expected=InsertionException.class)
    public void insertDuplicateEdge() {
        addVertices(graph);
        graph.insert(a, b, 10);
        graph.insert(a, b, 10);
    }

    /**
     REMOVE VERTEX AXIOMS and Pre
     */
    @Test
    public void removeVertexAxiom() {
        addVertices(graph);
        addEdges(graph);

        graph.remove(this.r);

        Iterable<Vertex<String>> vertices = graph.vertices();

        for (Vertex<String> v : vertices) {
            assertNotEquals(v, this.r);
        }
    }

    @Test(expected=RemovalException.class)
    public void removeVertexWithEdges() {
        addVertices(graph);
        addEdges(graph);

    	graph.remove(this.a);
    }

    @Test(expected=PositionException.class)
    public void removeBadVertex() {
        addVertices(graph);
        addEdges(graph);

        Graph<String,Integer> otherGraph = this.createGraph();

        Vertex<String> bad = otherGraph.insert("TEST");

    	graph.remove(bad);
    }

    /**
     REMOVE EDGE
     * @param e Edge position to remove.
     */

    @Test
    public void removeEdgeAxiom() {
        addVertices(graph);
        addEdges(graph);

    	graph.remove(this.h);

        Iterable<Edge<Integer>> edges = graph.edges();

        for (Edge<Integer> e : edges) {
            assertNotEquals(e, this.h);
        }
    }

    @Test(expected=PositionException.class)
    public void removeBadEdge() {
        addVertices(graph);
        addEdges(graph);

        graph.remove(this.h);
        graph.remove(this.h);
    }

    /**
    ITERABLES
    */

    /**
     * Vertices of graph.
     * @return Iterable over all vertices of the graph (in no specific order).
     */
    @Test
    public void vertexIterator() {

        Iterable<Vertex<String>> vIterator = graph.vertices();
        String vertices = "";
        for (Vertex<String> v : vIterator) {
            vertices += v.get();
        }
    }

    /**
     * Edges of graph.
     * @return Iterable over all edges of the graph (in no specific order).
     */
    @Test
    public void edgeIterator() {

        Iterable<Edge<Integer>> eIterator = graph.edges();
        String edges = "";
        for (Edge<Integer> e : eIterator) {
            edges += e.get();
        }
    }

    /**
     * Outgoing edges of vertex.
     * @param v Vertex position to explore.
     * @return Iterable over all outgoing edges of the given vertex
     *     (in no specific order).
     * @throws PositionException If vertex position is invalid.
     */
    @Test
    public void outgoingEdgeIterator() {
        addVertices(graph);

        Iterable<Edge<Integer>> outgoing = graph.outgoing(this.a);
        String edges = "";
        for (Edge<Integer> e : outgoing) {
            edges += e.get();
        }
    }
    
    @Test
    public void incomingEdgeIterator() {
        addVertices(graph);

        Iterable<Edge<Integer>> incoming = graph.incoming(this.a);
        String edges = "";
        for (Edge<Integer> e : incoming) {
            edges += e.get();
        }
    } //throws PositionException;

    /**
    FROM AND TO
    */

    @Test
    public void from() {
        addVertices(graph);
        addEdges(graph);
        Vertex<String> vertex = graph.from(this.h);
    }

    @Test
    public void to() {
        addVertices(graph);
        addEdges(graph);
        Vertex<String> vertex = graph.to(this.h);
    }

    /**
    LABELS 
    */

    @Test
    public void labelsInitiallyNull() {
        Iterable<Edge<Integer>> edges = graph.edges();
        Iterable<Vertex<String>> vertices = graph.vertices();
        for (Edge<Integer> e : edges) {
            graph.label(e, null);
        }
        for (Vertex<String> v : vertices) {
            graph.label(v, null);
        }
    }

    @Test //REPEATED
    public void setEdgeLabel() {
        addVertices(graph);
        addEdges(graph);

        Iterable<Edge<Integer>> edges = graph.edges();
        for (Edge<Integer> e : edges) {
            graph.label(e,"TEST");
        }
        for (Edge<Integer> e : edges) {
            assertEquals("TEST", graph.label(e));
        }
    }

    @Test
    public void labelVertexAxiom() {
    	addVertices(graph);
        addEdges(graph);

        assertEquals(null, graph.label(this.a));

        graph.label(this.a, "TEST");

        assertEquals("TEST", graph.label(this.a));
    }

    @Test(expected=PositionException.class)
    public void labelVertexPre() {
    	addVertices(graph);
        addEdges(graph);
        Vertex<String> x = graph.insert("TEST");
        graph.remove(x);
        graph.label(x, "TEST");
    }

    @Test
    public void labelEdgeAxiom() {
        addVertices(graph);
        addEdges(graph);

    	assertEquals(null, graph.label(this.h));

        graph.label(this.h, "TEST");

        assertEquals("TEST", graph.label(this.h));
    }

    @Test(expected=PositionException.class)
    public void labelEdgePre() {
        addVertices(graph);
        addEdges(graph);
        graph.remove(this.h);
        graph.label(this.h, "TEST");
    }

    @Test
    public void clearEdgeLabels() {
        Iterable<Edge<Integer>> edges = graph.edges();

        for (Edge<Integer> e : edges) {
            graph.label(e,"TEST");
        }
        for (Edge<Integer> e : edges) {
            assertEquals("TEST", graph.label(e));
        }

        graph.clearLabels();

        for (Edge<Integer> e : edges) {
            assertEquals(null, graph.label(e));
        }
    }
    @Test
    public void clearVertexLabels() {
        Iterable<Vertex<String>> vertices = graph.vertices();

        for (Vertex<String> v : vertices) {
            graph.label(v,"TEST");
        }
        for (Vertex<String> v : vertices) {
            assertEquals("TEST", graph.label(v));
        }

        graph.clearLabels();

        for (Vertex<String> v : vertices) {
            assertEquals(null, graph.label(v));
        }
    }
    @Test
    public void clearAllLabels() {
        addVertices(graph);
        addEdges(graph);

        graph.label(this.a,"TEST");
        graph.label(this.h, "TEST");

        assertEquals("TEST", graph.label(this.a));
        assertEquals("TEST", graph.label(this.h));

        graph.clearLabels();

        assertEquals(null, graph.label(this.a));
        assertEquals(null, graph.label(this.h));
    }
}