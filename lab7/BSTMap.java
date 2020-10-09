import java.util.Iterator;
import java.util.Set;
/**
* Class that implements Map61B interface using a BST.
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    /**
    * Nested Node class used for tree traversal in BSTMap.
     */
    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;
    private int size;

    /**
    * Creates empty BSTMap.
     */
    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Prints out  BSTMap in order of increasing Key.
     */
    public void printInOrder() {
        printInOrderHelper(root);
    }

    /**
     * Helper method for printInOrder(). Allows for
     * tree traversal using Nodes.
     */
    private void printInOrderHelper(Node node) {
        if (node == null) {
            return;
        }
        printInOrderHelper(node.left);
        if (node != null) {
            System.out.print(node.value + " ");
        }
        printInOrderHelper(node.right);
    }

    @Override
    /**
     * Iterator method. Not used.
     */
    public Iterator iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    private boolean containsKeyHelper(K key, Node node) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(key)) {
            return true;
        } else {
            int c = key.compareTo(node.key);
            if (c > 0) {
                return containsKeyHelper(key, node.right);
            } else if (c < 0) {
                return containsKeyHelper(key, node.left);
            } else {
                return true;
            }
        }
    }

    @Override
    /** Returns the number of key-value mappings in this map. */
    public int size() {
        return this.size;
    }

    @Override
    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Helper method for get(K key). Takes in Node as parameter
    /* alongside key to allow for tree traversal.
     */
    private V getHelper(K key, Node n) {
        if (n == null) {
            return null;
        }
        int c = key.compareTo(n.key);
        if (c < 0) {
            return getHelper(key, n.left);
        } else if (c > 0) {
            return getHelper(key, n.right);
        } else {
            return n.value;
        }
    }

    @Override
    /**
     * keySet method. Not used.
     */
    public Set keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    /** Removes the mapping for the specified key from this map if present.
     * Not used. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not used.
     */
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    /** Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        this.root = putHelper(key, value, this.root);
    }

    /** Helper method for put method. Allows for tree
     * traversal by taking Node as parameter in addition
     * to key and value.
     * */
    private Node putHelper(K key, V value, Node node) {
        if (node == null) {
            node = new Node(key, value);
        }
        int c = key.compareTo(node.key);
        if (c < 0) {
            node.left = putHelper(key, value, node.left);
        } else if (c > 0) {
            node.right = putHelper(key, value, node.right);
        } else {
            node.value = value;
            this.size += 1;
        }
        return node;
    }


}
