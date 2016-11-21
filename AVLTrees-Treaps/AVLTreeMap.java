//David Kleinberg
//dkleinb1@jhu.edu

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Ordered maps from comparable keys to arbitrary values.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AVLTreeMap<K extends Comparable<? super K>, V>
    implements OrderedMap<K, V> {

    // Inner node class, each holds a key (which is what we sort the
    // BST by) as well as a value. We don't need a parent pointer as
    // long as we use recursive insert/remove helpers.
    private class Node {
        Node left;
        Node right;
        K key;
        V value;
        int height;

        // Constructor to make node creation easier to read.
        Node(K k, V v) {
            // left and right default to null
            this.key = k;
            this.value = v;
        }

        private boolean hasLeft() {
            return this.left != null;
        }

        private boolean hasRight() {
            return this.right != null;
        }
        
    }

    private Node root;
    private int size;
    private StringBuilder stringBuilder;

    @Override
    public int size() {
        return this.size;
    }

    // Return node for given key. This one is iterative
    // but the recursive one from lecture would work as
    // well. (For simply finding a node there's no big
    // advantage to using recursion; I did recursion in
    // lecture to get you into the right mindset.)
    private Node find(K k) {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }
        Node n = this.root;
        while (n != null) {
            int cmp = k.compareTo(n.key);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else {
                return n;
            }
        }
        return null;
    }

    @Override
    public boolean has(K k) {
        if (k == null) {
            return false;
        }
        return this.find(k) != null;
    }

    // Return node for given key, throw an exception
    // if the key is not in the tree.
    private Node findForSure(K k) {
        Node n = this.find(k);
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }
        return n;
    }

    @Override
    public void put(K k, V v) {
        Node n = this.findForSure(k);
        n.value = v;
    }

    @Override
    public V get(K k) {
        Node n = this.findForSure(k);
        return n.value;
    }

    private int rightHeight(Node parent) {
        if (parent.hasRight()) {
            return parent.right.height;
        }
        return -1;
    }

    private int leftHeight(Node parent) {
        if (parent.hasLeft()) {
            return parent.left.height;
        }
        return -1;
    }

    private Node doubleRotateRight(Node pivot) {
        pivot.left = rotateLeft(pivot.left);
        return rotateRight(pivot);
    }

    private Node doubleRotateLeft(Node pivot) {
        pivot.right = rotateRight(pivot.right);
        return rotateLeft(pivot);
    }

    private Node rotateRight(Node pivot) {

        Node left = pivot.left;

        pivot.left = left.right;
        left.right = pivot;

        pivot.height = this.changeHeight(pivot);
        left.height = this.changeHeight(left);

        return left;
    }

    private Node rotateLeft(Node pivot) {

        Node right = pivot.right;

        pivot.right = right.left;
        right.left = pivot;

        pivot.height = this.changeHeight(pivot);
        right.height = this.changeHeight(right);

        return right;    
    }

    private int changeHeight(Node n) {

            if (this.leftHeight(n) > this.rightHeight(n)) {
                return this.leftHeight(n) + 1;

            } else {
                return this.rightHeight(n) + 1;
            }
    }

    private Node insert(Node n, K k, V v) {
        if (n == null) {
            return new Node(k, v);
        }

        int cmp = k.compareTo(n.key);

        if (cmp < 0) {

            n.left = this.insert(n.left, k, v);

            if (this.leftHeight(n) - this.rightHeight(n) > 1) {
                if (k.compareTo(n.left.key) < 0) {
                    n = this.rotateRight(n);
                } else {
                    n = this.doubleRotateRight(n);
                }
            }

        } else if (cmp > 0) {

            n.right = this.insert(n.right, k, v);

            if (this.rightHeight(n) - this.leftHeight(n) > 1) {
                if (k.compareTo(n.right.key) > 0) {
                    n = rotateLeft(n);
                } else {
                    n = doubleRotateLeft(n);
                }
            }
            
        } else {
            throw new IllegalArgumentException("duplicate key " + k);
        }

        n.height = this.changeHeight(n);
        return n;
    }

    @Override
    public void insert(K k, V v) {
        if (k == null) {
            throw new IllegalArgumentException("cannot handle null key");
        }

        this.root = this.insert(this.root, k, v);

        this.size += 1;
    }

    // Return node with maximum key in subtree rooted
    // at given node. (Iterative version because once
    // again recursion has no advantage here.)
    private Node max(Node n) {
        while (n.right != null) {
            n = n.right;
        }
        return n;
    }

    private Node successor(Node n) {

        n = n.right;

        while (n.hasLeft()) {
            n = n.left;
        }

        return n;
    }

    private Node makeNull(Node n, K k) {
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }

        int cmp = k.compareTo(n.key);
        
        if (cmp < 0) {
            n.left = this.makeNull(n.left, k);
        } else if (cmp > 0) {
            n.right = this.makeNull(n.right, k);
        } else {
            if (n.hasRight()) {
                n = n.right;
            } else {
                n = null;
            }
        }
        return n;

    }

    // Remove node with given key from subtree rooted at
    // given node; return changed subtree with given key
    // missing. (Once again doing this recursively makes
    // it easier to add fancy rebalancing code later.)
    private Node remove(Node n, K k) {
        if (n == null) {
            throw new IllegalArgumentException("cannot find key " + k);
        }

        int cmp = k.compareTo(n.key);
        
        if (cmp < 0) {
            n.left = this.remove(n.left, k);
            n.height = this.changeHeight(n);

        if (this.rightHeight(n) - this.leftHeight(n) > 1) {
                if (this.rightHeight(n.right) < this.leftHeight(n.right)) {
                    n = this.doubleRotateLeft(n);
                } else {
                    n = this.rotateLeft(n);
                }
            }
            n.height = this.changeHeight(n);

        } else if (cmp > 0) {
            n.right = this.remove(n.right, k);
            n.height = this.changeHeight(n);

        if (this.leftHeight(n) - this.rightHeight(n) > 1) {
                if (this.leftHeight(n.left) < this.rightHeight(n.left)) {
                    n = this.doubleRotateRight(n);
                } else {
                    n = this.rotateRight(n);
                }
            }
        n.height = this.changeHeight(n);

        } else {
            n = this.remove(n);
        }
        return n;
    }

    // Remove given node and return the remaining tree.
    // Easy if the node has 0 or 1 child; if it has two
    // children, find the predecessor, copy its data to
    // the given node (thus removing the key we need to
    // get rid off), the remove the predecessor node.
    private Node remove(Node n) {
        // 0 and 1 child
        if (n.left == null) {
            return n.right;
        }
        if (n.right == null) {
            return n.left;
        }

        // 2 children
        Node successor = this.successor(n);
        this.makeNull(this.root, successor.key);
        n.key = successor.key;
        n.value = successor.value;
        n.height = this.changeHeight(n);
        return n;
    }

    @Override
    public V remove(K k) {
        // Need this additional find() for the V return value, because the
        // private remove() method cannot return that in addition to the new
        // root. If we had been smarter and used a void return type, we would
        // not need to do this extra work.
        Node n = this.find(k);
        this.root = this.remove(this.root, k);
        this.root.height = this.changeHeight(this.root);
        this.size -= 1;
        return n.value;
    }

    // Recursively add keys from subtree rooted at given node
    // into the given list.
    private void iteratorHelper(Node n, List<K> keys) {
        if (n == null) {
            return;
        }
        this.iteratorHelper(n.left, keys);
        keys.add(n.key);
        this.iteratorHelper(n.right, keys);
    }

    @Override
    public Iterator<K> iterator() {
        List<K> keys = new ArrayList<K>();
        this.iteratorHelper(this.root, keys);
        return keys.iterator();
    }

    // If we don't have a StringBuilder yet, make one;
    // otherwise just reset it back to a clean slate.
    private void setupStringBuilder() {
        if (this.stringBuilder == null) {
            this.stringBuilder = new StringBuilder();
        } else {
            this.stringBuilder.setLength(0);
        }
    }

    // Recursively append string representations of keys and
    // values from subtree rooted at given node.
    private void toStringHelper(Node n, StringBuilder s) {
        if (n == null) {
            return;
        }
        this.toStringHelper(n.left, s);
        s.append(n.key);
        s.append(": ");
        s.append(n.value);
        s.append(", ");
        this.toStringHelper(n.right, s);
    } 

    @Override
    public String toString() {
        this.setupStringBuilder();
        this.stringBuilder.append("{");

        this.toStringHelper(this.root, this.stringBuilder);

        int length = this.stringBuilder.length();
        if (length > 1) {
            // If anything was appended at all, get rid of
            // the last ", " the toStringHelper put in.
            this.stringBuilder.setLength(length - 2);
        }
        this.stringBuilder.append("}");

        return this.stringBuilder.toString();
    }
}
