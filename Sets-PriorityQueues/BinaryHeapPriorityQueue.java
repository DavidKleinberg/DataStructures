//David Kleinberg
//dkleinb1@jhu.edu

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Priority queue implemented as a sorted ArrayList.
 *
 * We use binary search to find the insertion point in O(log n) time, but
 * we need to spend O(n) time to "make room" for the insertion. We don't
 * treat the array as cyclic, so remove() also takes O(n) time...
 *
 * We use slot 0 of the ArrayList as a "sentinel" of sorts for internal
 * find() calls.
 *
 * @param <T> Element type.
 */
public class BinaryHeapPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueue<T> {

    // The default comparator uses the "natural" ordering.
    private static class DefaultComparator<T extends Comparable<? super T>>
        implements Comparator<T> {
        public int compare(T t1, T t2) {
            return t1.compareTo(t2);
        }
    }

    private ArrayList<T> data;
    private Comparator<T> cmp;
    private int bottomRight;

    /**
     * A sorted array using the "natural" ordering of T.
     */
    public BinaryHeapPriorityQueue() {
        this(new DefaultComparator<>());
    }

    /**
     * A sorted array using the given comparator for T.
     * @param cmp Comparator to use.
     */
    public BinaryHeapPriorityQueue(Comparator<T> cmp) {
        this.data = new ArrayList<T>();
        this.data.add(null);
        this.bottomRight = 1;
        this.cmp = cmp;
    }

    // Value in slot i "less" than value in slot j? Note that the
    // comparator determines what we consider "less" here.
    private boolean less(int i, int j) {
        return this.cmp.compare(this.data.get(i), this.data.get(j)) < 0;
    }

    @Override
    public void insert(T t) {
        this.data.add(this.bottomRight, t);
        int index = this.bottomRight;
        this.bottomRight++;
        while (index != 1 && this.less(index, index / 2)) {
            T child = this.data.get(index);
            this.data.set(index, this.data.get(index / 2));
            this.data.set(index / 2, child);
            index = index / 2;
        }
    }

    @Override
    public void remove() throws EmptyException {

        if (this.empty()) {
            throw new EmptyException();
        }

        this.bottomRight--;

        T bottom = this.data.get(this.bottomRight);
        this.data.set(1, bottom);
        this.data.remove(this.bottomRight);

        int index = 1;

        while (index * 2 < this.bottomRight - 1 &&
        	  (this.less(index * 2, index) || this.less(index * 2 + 1, index))) {

            T parent = this.data.get(index);
            T left = this.data.get(index * 2);
            T right = this.data.get(index * 2 + 1);

            if (this.less(index * 2 + 1, index * 2)) {
                this.data.set(index, right);
                this.data.set(index * 2 + 1, parent);
                index = index * 2 + 1;
            } else {
                this.data.set(index, left);
                this.data.set(index * 2, parent);
                index = index * 2;
            }
        }

    }

    @Override
    public T top() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.data.get(1);
    }

    @Override
    public boolean empty() {
        return this.data.size() == 1;
    }
}
