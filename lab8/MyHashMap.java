import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private class Node {
        K key;
        V value;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int size;
    private ArrayList<Node>[] buckets;
    private double loadFactor;
    private HashSet<K> keys;

    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private static final int DEFAULT_INITIAL_SIZE = 16;

    /**
     * Constructors below for MyHashMap implementation.
     */
    public MyHashMap() {
        this(DEFAULT_INITIAL_SIZE, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialSize, double maxLoad) {
        buckets = (ArrayList<Node>[]) new ArrayList[initialSize];
        size = 0;
        loadFactor = maxLoad;
        keys = new HashSet<K>();
    }

    /**
     * Resets the HashMap.
     */
    @Override
    public void clear() {
        buckets = (ArrayList<Node>[]) new ArrayList[DEFAULT_INITIAL_SIZE];
        size = 0;
        keys = new HashSet<>();
    }

    /**
     * Method to check if a key exists in HashMap.
     * @param key the given key one wants to search for.
     * @return true if found, false if not.
     */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /**
     * Retrieves value associated with key, null if not found.
     * @param key the key to be retrieved.
     * @return the value associated with the key.
     */
    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node getNode(K key) {
        int idx = findBucket(key);
        ArrayList<Node> bucketList = buckets[idx];
        if (bucketList != null) {
            for (Node node: bucketList) {
                if (node.key.equals(key)) {
                    return node;
                }
            }
        }
        return null;
    }

    /**
     * Helper methods for determining indexing of buckets.
     * @param key the key of the item.
     * @return integer of the index of the bucket its associated value is located.
     */
    private int findBucket(K key, int numBuckets) {
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    private int findBucket(K key) {
        return findBucket(key, buckets.length);
    }

    private void rebucket(int targetSize) {
        ArrayList<Node>[] newBuckets = (ArrayList<Node>[]) new ArrayList[targetSize];
        for (K key: keys) {
            int idx = findBucket(key, newBuckets.length);
            if (newBuckets[idx] == null) {
                newBuckets[idx] = new ArrayList<>();
            }
            newBuckets[idx].add(getNode(key));
        }
        buckets = newBuckets;
    }

    /**
     *
     * @return number of key-value mappings in this map. */

    @Override
    public int size() {
        return size;
    }

    /**
     * Insertion method for HashMap.
     * @param key key of item to be inserted.
     * @param value value of item to be inserted.
     */
    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
            return;
        }
        if (((double) size) / buckets.length > loadFactor) {
            rebucket(buckets.length * 2);
        }
        size += 1;
        keys.add(key);
        int idx = findBucket(key);
        ArrayList<Node> bucketList = buckets[idx];
        if (bucketList == null) {
            bucketList = new ArrayList<>();
            buckets[idx] = bucketList;
        }
        bucketList.add(new Node(key, value));
    }



    /**
     *
     * @return Set of all the keys in HashMap.
     */
    @Override
    public Set<K> keySet() {
        return keys;
    }

    /**
     * Unused.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("exception!");
    }

    /**
     * Unused.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("exception!");
    }

    /**
     *
     * @return Iterator associated with the Set of keys created earlier
     * of all keys in the HashMap.
     */
    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
