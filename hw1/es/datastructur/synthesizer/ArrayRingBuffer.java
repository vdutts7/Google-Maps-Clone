package es.datastructur.synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;
    /* Capacity for buffer. */
    private int capacity;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.capacity = capacity;
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.rb = (T[]) new Object[this.capacity];
    }

    /**
     * Returns buffer's capacity.
     */
    @Override
    public int capacity() {
        return capacity;
    }

    /**
     * Returns buffer's fillCount.
     */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Returns true only if the other object is an
     * ArrayRingBuffer with the exact same values.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        ArrayRingBuffer<T> other = (ArrayRingBuffer<T>) o;
        if (this.capacity() != other.capacity()) {
            return false;
        }
        for (int i = 0; i < this.capacity(); i++) {
            if (this.dequeue() != other.dequeue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;

        if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }

        fillCount += 1;
    }


    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first. Don't worry about throwing the RuntimeException until you
        //       get to task 4.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer underflow");
        }

        T oldestItem = rb[first];
        rb[first] = null;

        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }

        fillCount -= 1;

        return oldestItem;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        return rb[first];
   }

    /**
     * Creates new iterator for ArrayRingBuffer.
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    /**
     * Iterator class for ArrayRingBuffers. Implements Iterator</T>.
     */
    private class ArrayRingBufferIterator implements Iterator<T> {
        int current; //pointer value

        public ArrayRingBufferIterator() {
            current = 0;
        }
        @Override
        public T next() {
            T nextValue = rb[current];
            current += 1;
            return nextValue;
        }

        @Override
        public boolean hasNext() {
            return !(current == capacity);
        }
    }
}
