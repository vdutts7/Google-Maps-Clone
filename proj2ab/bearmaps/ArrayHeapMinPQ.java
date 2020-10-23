package bearmaps;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * Class for ArrayList based Min Heap PQ data structure.
 * @param <T> generic type for ArrayHeapMinPQ data structure
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    ArrayList<PriorityNode> items;
    HashMap<T, Integer> hashMap;
    int size;

    /**
     * Constructor method. Hashmap used for storing and
     * tracking items and index in heap.
     */
    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        items.add(null);
        hashMap = new HashMap<>();
    }

    /**
     * Adds items with given priority in heap according to min-heap rule.
     * @param item item added
     * @param priority item's prioirity in heap
     */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        size += 1;
        hashMap.put(item, size);
        swim(size);
    }

    /**
     * Tells if heap contains given item or not.
     * @param item item being searched
     * @return true if item in heap, false if not
     */
    @Override
    public boolean contains(T item) {
        return hashMap.containsKey(item);
    }

    /**
     * Finds and returns item of lowest priority in heap.
     * @return item of lowest priority in heap
     */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            return items.get(1).item;
        }
    }

    /**
     * Finds, removes, and returns item of lowest priority in heap.
     * @return item of lowest priority in heap before removing from heap.
     */
    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        } else {
            PriorityNode tempNode = items.get(1);
            swapItems(1, size);

            items.remove(size);
            size -= 1;

            hashMap.remove(tempNode.item);
            sink(1);
            return tempNode.item;
        }

    }

    /**
     * Used to access current size of heap.
     * @return current size of heap
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Changes priority of a given item in heap.
     * @param item item wishing to be changed in its priority
     * @param priority the new priority the item should take on
     */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        } else {
            items.get(hashMap.get(item)).setPriority(priority);
            swim(hashMap.get(item));
            sink(hashMap.get(item));
        }
    }

    /**
     * Private helper method for quickly determining index of parent.
     * @param currentIndex index of item at current point in traversal
     * @return index of parent
     */
    private int parentIndex(int currentIndex) {
        return currentIndex / 2;
    }

    /**
     * Private helper method for quickly determining index of left child.
     * @param currentIndex index of item at current point in traversal
     * @return index of left child
     */
    private int leftIndex(int currentIndex) {
        return 2 * currentIndex;
    }

    /**
     * Private helper method for quickly determining index of right child.
     * @param currentIndex index of item at current point in traversal
     * @return index of right child
     */
    private int rightIndex(int currentIndex) {
        return 1 + 2 * currentIndex;
    }

    /**
     * Private helper method for "swimming" or moving items upwards
     * in heap upon addition.
     * @param currentIndex index of item at current point in traversal
     */
    private void swim(int currentIndex) {
        if (currentIndex == 1) {
            return;
        }
        int cmp = items.get(currentIndex).compareTo(items.get(parentIndex(currentIndex)));
        if (cmp < 0) {
            swapItems(parentIndex(currentIndex), currentIndex);
            swim(parentIndex(currentIndex));
        }
    }

    /**
     * Private helper method for "sinking" or moving items downwards
     * in heap upon addition.
     * @param currentIndex index of item at current point in traversal
     */
    private void sink(int currentIndex) {
        while(rightIndex(currentIndex) < (size + 1) && leftIndex(currentIndex) < (size + 1)) {
            int cmpLeftIndex = items.get(currentIndex).compareTo(items.get(leftIndex(currentIndex)));
            int cmpRightIndex  = items.get(currentIndex).compareTo(items.get(rightIndex(currentIndex)));

            if (cmpLeftIndex < 1 && cmpRightIndex < 1) {
                return;
            } else if (cmpLeftIndex > 0 && cmpRightIndex > 0 && cmpLeftIndex > cmpRightIndex) {
                swapItems(currentIndex, leftIndex(currentIndex));
                currentIndex = leftIndex(currentIndex);
            } else if (cmpLeftIndex > 0 && cmpRightIndex > 0 && cmpLeftIndex < cmpRightIndex) {
                swapItems(currentIndex, rightIndex(currentIndex));
                currentIndex = rightIndex(currentIndex);
            } else if (cmpLeftIndex > 0) {
                swapItems(currentIndex, leftIndex(currentIndex));
                currentIndex = leftIndex(currentIndex);
            } else {
                swapItems(currentIndex, rightIndex(currentIndex));
                currentIndex = rightIndex(currentIndex);
            }
        }
    }

    /**
     * Private helper method for swapping two items in heap.
     * @param index1 index of first item
     * @param index2 index of second item
     */
    private void swapItems(int index1, int index2) {
        PriorityNode temp = items.get(index2);
        items.set(index2, items.get(index1));
        items.set(index1, temp);

        hashMap.put(items.get(index2).getItem(), index1);
        hashMap.put(items.get(index1).getItem(), index2);
    }

    /**
     * Nested helper class representing Node-like data storage container
     * for items and respective priorities. Used as generic type for ArrayList
     * in the min heap PQ.
     */
    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }

}
