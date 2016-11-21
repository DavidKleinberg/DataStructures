//David Kleinberg
//dkleinb1@jhu.edu

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Move to Front Heuristic.
 * @param <T> Element type.
 */
public class MoveToFrontListSet<T> implements Set<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;
    }

    private Node<T> head;
    private int version;

    private class SetIterator implements Iterator<T> {
        private Node<T> current;
        private int version;

        SetIterator() {
            this.current = MoveToFrontListSet.this.head;
            this.version = MoveToFrontListSet.this.version;
        }

        private void checkVersion() throws ConcurrentModificationException {
            if (this.version != MoveToFrontListSet.this.version) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public boolean hasNext() {
            this.checkVersion();
            return this.current != null;
        }

        @Override
        public T next() {
            this.checkVersion();
            T t = this.current.data;
            this.current = this.current.next;
            return t;
        }

        @Override
        public void remove() {
            this.checkVersion();
            throw new UnsupportedOperationException();
        }
    }

    private Node<T> find(T t) {
        for (Node<T> n = this.head; n != null; n = n.next) {
            if (n.data.equals(t)) {
                if (n != this.head) {
                    if (n.next != null) {
                        n.next.prev = n.prev;
                    }
                    n.prev.next = n.next;
                    n.next = this.head;
                    this.head.prev = n;
                    n.prev = null;
                    this.head = n;
                }
                return n;
            }
        }
        return null;
    }

    @Override
    public void insert(T t) {
        if (this.has(t)) {
            return;
        }
        Node<T> n = new Node<T>();
        n.data = t;
        n.next = this.head;
        n.prev = null;
        if (this.head != null) {
            this.head.prev = n;
        }
        this.head = n;
        this.version++;
    }

    @Override
    public void remove(T t) {
        Node<T> position = this.find(t);
        if (position == null) {
            return;
        }
        if (position.next != null) {
            position.next.prev = position.prev;
        }
        if (position.prev != null) {
            position.prev.next = position.next;
        } else {
            this.head = position.next;
        }
        this.version++;
    }

    @Override
    public boolean has(T t) {
        return this.find(t) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }
}
