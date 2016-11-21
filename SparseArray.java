//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Iterator;

/**
    Array implementation using a linked list.

    @param <T> Element type.
*/
public class SparseArray<T> implements Array<T> {

    /** A nested Node class to build our linked list out of. We use a nested
       class (instead of an inner class) here since we don't need access to the
       SparseArray object we're part of.
       @param <T> Element type.
     */
    private static class Node<T> {
        T data;
        Node<T> next;
        int position;
    }

    //the front of the list, null for empty list
    private Node<T> first;

    //the length of the list (not the number of Nodes)
    private int length;

    //the value to initial each index with
    private T initial;

    //the number of Nodes (used in remove method)
    private int numNodes;

    /** An iterator to traverse the array from front to back. Note that we use
     * an inner class here (not a nested class) so we can access the outer
     * object's "this" reference. If we didn't do that, we'd have to pass the
     * outer object (or the first node) to the iterator in its constructor.
     */
    private class ArrayIterator implements Iterator<T> {
        // Current position in the linked list.
        Node<T> current;

        ArrayIterator() {
            this.current = SparseArray.this.first;
        }

        @Override
        public T next() {
            T t = this.current.data;
            this.current = this.current.next;
            return t;
        }

        /** returns the node.*/
        private Node<T> getNext() {
            Node<T> t = this.current;
            this.current = this.current.next;
            return t;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
        Constructs a new SparseArray.

        @param n Length of array
        @param t Default value to store in each slot
        @throws LengthException if n is <= 0
    */
    public SparseArray(int n, T t) throws LengthException {
        if (n <= 0) {
            throw new LengthException();
        }

        this.initial = t;
        // Remember the length!
        this.length = n;
    }

    // Insert a node at the beginning of the linked list.
    private void prepend(int p, T t) {
        Node<T> n = new Node<T>();
        n.data = t;
        n.position = p;
        n.next = this.first;
        this.first = n;
        this.numNodes++;
    }

    /** Remove a node if its data is set back to the initial value.*/
    private void remove(int i) {
        ArrayIterator iterator = new ArrayIterator();
        Node<T> x = this.find(i);
        if (numNodes == 1) {
            x = null;
        } else {
            while (iterator.hasNext()) {
                Node<T> y = iterator.getNext();
                if (y.next == x) {
                    y.next = x.next;
                }
            }
        }
        numNodes--;
    }

    // Find the node for a given index. We enforce the precondition on index
    // here so we don't have to duplicate the check in get() and put() below.
    private Node<T> find(int index) throws IndexException {
        if (index < 0 || index >= this.length) {
            throw new IndexException();
        }
        ArrayIterator iterator = new ArrayIterator();
        while (iterator.hasNext()) {
            Node<T> n = iterator.getNext();
            if (n.position == index) {
                return n;
            }
        }
        return null;
    }

    @Override
    public T get(int i) throws IndexException  {
        Node<T> n = this.find(i);
        if (n != null) {
            return n.data;
        }
        return this.initial;
    }

    @Override
    public void put(int i, T t) throws IndexException  {
        Node<T> n = this.find(i);
        if (this.find(i) != null && t != this.initial) {
            n.data = t;
        }
        if (this.find(i) == null && t != this.initial) {
            this.prepend(i, t);
        }
        if (this.find(i) != null && t == this.initial) {
            this.remove(i);
        }
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }
}
