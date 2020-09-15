/** Class representing ArrayDeque, a double-ended que powered by an Array. */
public class ArrayDeque<T> {
    private static int startingSize = 8;
    private int size;

    /**
     * Private helper class variables for later introduced
     * private helper methods. items refers to array of generic
     * type T, nextStart refers to next element at start, nextEnd
     * refers to end element at end.
     */
    private T[] items;
    private int nextStart;
    private int nextEnd;

    /** Private helper methods below. */

    /** Private helper method: calculates and returns index after an index. */
    private int indexAfter(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return 1 + index;
        }
    }

    /** Private helper method: calculates and returns index before an index. */
    private int indexBefore(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    /** Private helper method: changes size of array based on new size, passed in as paramater. */
    private void changeSize(int adjustedSize) {
        T[] holder = (T[]) new Object[adjustedSize];

        if (nextEnd == indexAfter(nextStart)) {
            System.arraycopy(items, indexAfter(nextStart), holder, 1,
                    items.length - indexAfter(nextStart));
            System.arraycopy(items, 0, holder, 1 + items.length - indexAfter(nextStart), nextEnd);
        } else if (nextStart > nextEnd) {
            System.arraycopy(items, indexAfter(nextStart), holder, 1, items.length - nextStart - 1);
            System.arraycopy(items, 0, holder, items.length - nextStart, nextEnd);
        } else {
            System.arraycopy(items, 1 + nextStart, holder, 1, size);
            nextStart = 0;
            nextEnd = 1 + size;
            items = holder;
        }
    }

    /** Constructor for empty ArrayDeque. */
    public ArrayDeque() {
        items = (T[]) new Object[startingSize];
        nextStart = startingSize / 2;
        nextEnd = nextStart + 1;

        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        if (size == items.length) {
            changeSize(2 * size);
        } else {
            items[nextStart] = item;
            size += 1;
            nextStart = indexBefore(nextStart);
        }
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        if (size == items.length) {
            changeSize(2 * size);
        } else {
            items[nextEnd] = item;
            size += 1;
            nextEnd = indexAfter(nextEnd);
        }
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     * separated by a space. Once all the items have been
     * printed, print out a new line.
     */
    public void printDeque() {
        int indexCurr = indexAfter(nextStart);
        while (indexCurr != nextEnd) {
            System.out.print(items[indexCurr] + " ");
            indexCurr = indexAfter(indexCurr);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            T returnValue = items[indexAfter(nextStart)];

            items[indexAfter(nextStart)] = null;
            nextStart = indexAfter(nextStart);

            size -= 1;
            if (items.length > 8 && size == items.length / 4) {
                changeSize(items.length / 2);
            }
            return returnValue;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            T returnValue = items[indexBefore(nextEnd)];

            items[indexBefore(nextEnd)] = null;
            nextEnd = indexBefore(nextEnd);

            size -= 1;
            if (items.length > 8 && size == items.length / 4) {
                changeSize(items.length / 2);
            }
            return returnValue;
        }

    }

    /**Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque.
     */
    public T get(int index) {
        if (index > (size - 1) || index < 0) {
            return null;
        } else {
            return items[(1 + index + nextStart) % items.length];
        }
    }

}