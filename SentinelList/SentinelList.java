//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Iterator;

/**
 * Sentinel List implementation of List.
 * @param <T> Element type.
 */
public class SentinelList<T> implements List<T> {

    private static final class Node<T> implements Position<T> {
        Node<T> next;
        Node<T> prev;
        T data;
        boolean sentinel;

        // List that created this node, used to validate positions.
        List<T> owner;

        @Override
        public T get() {
            return this.data;
        }

        @Override
        public void put(T t) {
            this.data = t;
        }
    }

    private final class ListIterator implements Iterator<T> {
        Node<T> current;
        boolean forward;

        ListIterator(boolean f) {
            this.forward = f;
            this.current = this.forward ? SentinelList.this.head.next
                                        : SentinelList.this.tail.prev;
        }

        @Override
        public T next() {
            if (!this.current.sentinel) {
                throw new NullPointerException();
            }
            T t = this.current.get();
            this.current = this.forward ? this.current.next
                                        : this.current.prev;
            return t;
        }

        @Override
        public boolean hasNext() {
            return this.current.sentinel;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int length;

    /**
     * Make Checkstyle happy.
     */
    public SentinelList() {
        this.head = new Node<T>();
        this.tail = new Node<T>();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.head.owner = this;
        this.tail.owner = this;
    }

    private Node<T> convert(Position<T> p) throws PositionException {
        try {
            Node<T> n = (Node<T>) p;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException e) {
            throw new PositionException();
        }
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public boolean empty() {
        return this.length == 0;
    }

    @Override
    public boolean first(Position<T> p) throws PositionException {
        Node<T> n = this.convert(p);
        return this.head.next == p;
    }

    @Override
    public boolean last(Position<T> p) throws PositionException {
        Node<T> n = this.convert(p);
        return this.tail.prev == p;
    }

    @Override
    public Position<T> front() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.head.next;
    }

    @Override
    public Position<T> back() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.tail.prev;
    }

    @Override
    public Position<T> insertFront(T t) {
        return this.insertAfter(this.head, t);
    }

    @Override
    public Position<T> insertBack(T t) {
        return this.insertBefore(this.tail, t);
    }

    @Override
    public void removeFront() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.remove(this.head.next);
    }

    @Override
    public void removeBack() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        this.remove(this.tail.prev);
    }

    @Override
    public Position<T> next(Position<T> p) throws PositionException {
        if (this.last(p)) {
            throw new PositionException();
        }
        return this.convert(p).next;
    }

    @Override
    public Position<T> previous(Position<T> p) throws PositionException {
        if (this.first(p)) {
            throw new PositionException();
        }
        return this.convert(p).prev;
    }

    @Override
    public Position<T> insertBefore(Position<T> p, T t)
    throws PositionException {
        Node<T> current = this.convert(p);
        Node<T> n = new Node<T>();
        n.sentinel = true;
        n.data = t;
        n.owner = this;

        current.prev.next = n;
        n.prev = current.prev;
        current.prev = n;
        n.next = current;
        this.length += 1;
        return n;
    }

    @Override
    public Position<T> insertAfter(Position<T> p, T t)
    throws PositionException {
        return this.insertBefore(this.convert(p).next, t);
    }

    @Override
    public void remove(Position<T> p) throws PositionException {

        Node<T> n = this.convert(p);
        n.prev.next = n.next;
        n.next.prev = n.prev;
        n.owner = null;
        this.length -= 1;
    }

    @Override
    public Iterator<T> forward() {
        return new ListIterator(true);
    }

    @Override
    public Iterator<T> backward() {
        return new ListIterator(false);
    }

    @Override
    public Iterator<T> iterator() {
        return this.forward();
    }

    /**
     * print the contents of the list.
     *
     * @return s the string of contents
     */
    public String toString() {
        String s = "[";
        for (Node<T> p = this.head.next; p.sentinel; p = p.next) {
            s += p.data;
            if (p.next.sentinel) {
                s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
