/** Class representing LinkedListDeque, a double-ended que powered by a LinkedList. */
public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    /** Nested class for a generic Node, characteristic of LinkedList data structures. */
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node m, T i, Node n) {
            prev = m;
            item = i;
            next = n;
        }
    }

     /** Constructor for a LinkedListDeque, creating empty linked list deque.
      * Setting sentinel as null and then changing previous and next values to
      * the sentinel to serve its purpose as a circular sentinel. The
     * sentinel.next.item refers to the front of the queue while the
     * sentinel.prev.item refers to the back of the queue.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);

        sentinel.next = sentinel;
        sentinel.prev = sentinel;

        size = 0;
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;

        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;

        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if ((size == 0) && (sentinel == sentinel.next) && (sentinel == sentinel.prev)) {
            return true;
        } else {
            return false;
        }
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
        Node val = sentinel;

        while(val.next != sentinel) {
            System.out.print(val.next.item + " ");
            val = val.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (sentinel == sentinel.next) {
            return null;
        } else {
            T returnValue = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;

            size -= 1;
            return returnValue;
        }
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (sentinel == sentinel.prev) {
            return null;
        } else {
            T returnValue = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;

            size -= 1;
            return returnValue;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. If no such item exists,
     * returns null. Must not alter the deque.
     */
    public T get(int index) {
        if (size <= index) {
            return null;
        } else {
            Node val = sentinel.next;

            while (index != 0) {
                val = val.next;
                index -= 1;
            }

            return val.item;
        }
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (size <= index) {
            return null;
        } else if (index == 0) {
            return sentinel.next.item;
        } else {
            sentinel.next = sentinel.next.next;
            return getRecursive(index);
        }
    }

}
