package implementations;

import utilities.QueueADT;

/**
 * Queue (FIFO - First In, First Out) of elements, using `MyDLL`
 *
 * @param <E> the type of elements held in this queue
 */
public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> queue; // Use MyDLL

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        queue = new MyDLL<>();
    }

    /**
     * Adds an element to the end of the queue.
     *
     * @param element the element to be added
     */
    @Override
    public void enqueue(E element) {
        queue.add(element);
    }

    /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element removed from the front of the queue, or null if the queue is empty
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    /**
     * Returns the element at the front of the queue without removing it.
     *
     * @return the element at the front of the queue, or null if the queue is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.get(0);
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the size of the queue
     */
    @Override
    public int size() {
        return queue.size();
    }

    /**
     * Removes all elements from the queue.
     */
    @Override
    public void dequeueAll() {
        queue.clear();
    }
}
