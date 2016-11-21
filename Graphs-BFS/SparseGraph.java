//David Kleinberg
//dkleinb1@jhu.edu

import java.util.ArrayList;

public final class SparseGraph<V, E> implements Graph<V, E> {

	private ArrayList<VNode<V>> vertexList = new ArrayList<VNode<V>>();
	private int numVertices;

	public SparseGraph() {}

	private final class VNode<V> implements Vertex<V> {
        SparseGraph<V,E> owner;
        ArrayList<ENode<E>> inList = new ArrayList<ENode<E>>();
        ArrayList<ENode<E>> outList = new ArrayList<ENode<E>>();
        Object label;
        V data;
		
		@Override
    	public V get() {
    		return this.data;
    	}

    	@Override
    	public void put(V v) {
    		this.data = v;
    	}
	}

	private final class ENode<E> implements Edge<E> {
		SparseGraph<V,E> owner;
        VNode<V> inVertex;
		VNode<V> outVertex;
        Object label;
        E data;
		
		@Override
    	public E get() {
    		return this.data;
    	}

    	@Override
    	public void put(E e) {
    		this.data = e;
    	}
	}

	@Override
    public Vertex<V> insert(V v) {
        VNode<V> vertex = new VNode<V>();
        vertex.data = v;
        vertex.owner = this;
        this.vertexList.add(vertex);
        return vertex;
    }

    @Override
    public Edge<E> insert(Vertex<V> from, Vertex<V> to, E e)
        throws PositionException, InsertionException {
        
        VNode<V> in = this.convert(to);
        VNode<V> out = this.convert(from);
        ArrayList<ENode<E>> inList = in.inList;
        ArrayList<ENode<E>> outList = out.outList;

        if (from == to) {
            throw new InsertionException();
        }

        for (ENode<E> enode : inList) {
            if (enode.inVertex.equals(in) && enode.outVertex.equals(out)) {
                throw new InsertionException();
            }
        }
        for (ENode<E> enode : outList) {
            if (enode.inVertex.equals(in) && enode.outVertex.equals(out)) {
                throw new InsertionException();
            }
        }

        ENode<E> edge = new ENode<E>();
        edge.inVertex = in; 
        edge.outVertex = out;
        edge.data = e;
        edge.owner = this;
        inList.add(edge);
        outList.add(edge);
        return edge;
    }

    private VNode<V> convert(Vertex<V> v) throws PositionException {
        try {
            VNode<V> n = (VNode<V>)v;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException e) {
            throw new PositionException();
        }
    }

    private ENode<E> convert(Edge<E> e) throws PositionException {
        try {
            ENode<E> n = (ENode<E>) e;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException f) {
            throw new PositionException();
        }
    }

    @Override
    public V remove(Vertex<V> v) throws PositionException, RemovalException {
        VNode<V> vertex = this.convert(v);
        if(!vertex.outList.isEmpty() || !vertex.inList.isEmpty()) {
            throw new RemovalException();
        } else {
            vertex.owner = null;
            this.vertexList.remove(this.vertexList.indexOf(vertex));
        }
    	return vertex.data;
    }

    @Override
    public E remove(Edge<E> e) throws PositionException {
    	ENode<E> edge = this.convert(e);
    	edge.owner = null;
    	edge.inVertex.inList.remove(edge);
    	edge.outVertex.outList.remove(edge);
    	return edge.data;
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
    	return (Iterable<Vertex<V>>)this.vertexList.clone();
    }

    @Override
    public Iterable<Edge<E>> edges() {
    	ArrayList<ENode<E>> edges = new ArrayList<ENode<E>>();
    	for (VNode<V> v : this.vertexList) {
                edges.addAll(v.inList);
    	}
    	return (Iterable<Edge<E>>)edges.clone();
    }

    @Override
    public Iterable<Edge<E>> outgoing(Vertex<V> v) throws PositionException {
        VNode<V> vertex = this.convert(v);
        return (Iterable<Edge<E>>)vertex.outList.clone();
    }

    @Override
    public Iterable<Edge<E>> incoming(Vertex<V> v) throws PositionException {
    	VNode<V> vertex = this.convert(v);
        return (Iterable<Edge<E>>)vertex.inList.clone();
    }

    @Override
    public Vertex<V> from(Edge<E> e) throws PositionException {
        ENode<E> edge = this.convert(e);
    	return edge.outVertex;
    }

    @Override
    public Vertex<V> to(Edge<E> e) throws PositionException {
        ENode<E> edge = this.convert(e);
    	return edge.inVertex;
    }

    @Override
    public void label(Vertex<V> v, Object l) throws PositionException {
    	VNode<V> vertex = this.convert(v);
        vertex.label = l;
    }

    @Override
    public void label(Edge<E> e, Object l) throws PositionException {
    	ENode<E> edge = this.convert(e);
        edge.label = l;
    }

    @Override
    public Object label(Vertex<V> v) throws PositionException {
    	VNode<V> vertex = this.convert(v);
    	return vertex.label;
    }

    @Override
    public Object label(Edge<E> e) throws PositionException {
    	ENode<E> edge = this.convert(e);
    	return edge.label;
    }

    @Override
    public void clearLabels() {
        for (Edge<E> e : this.edges()) {
            this.convert(e).label = null;
        }
        for (Vertex<V> v : this.vertices()) {
            this.convert(v).label = null;
        }
    }

    public String toString() {
    	String string = "digraph {\n";
        String edgeString = "";
    	for (VNode<V> v : this.vertexList) {
            string += "\t\"" + v.data + "\";\n";

  			for (ENode<E> e : v.outList) {
  				edgeString += "\t\"" + v.data + "\" -> \"" + e.inVertex.data + "\" [label=\"" + e.data + "\"];\n";
  			}
    	}
    	string += edgeString + "}";
    	return string;
    }
}