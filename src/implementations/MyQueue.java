package implementations;

import exceptions.EmptyQueueException;
import utilities.Iterator;
import utilities.QueueADT;


//Author Nirbhay Vachhani
/**
 * Queue (FIFO - First In, First Out) of elements, using `MyDLL`
 *  
 * @param <E> the type of elements held in this queue
 */
public class MyQueue<E> implements QueueADT<E> {
    private MyDLL<E> queue;

    /**
     * Constructs an empty queue.
     */
    public MyQueue() {
        queue = new MyDLL<>();
    }
    
     /**Adds an element to the end of the queue.
     *
     * @param element the element to add
     * @throws NullPointerException if the element is null
     */
    @Override
    public void enqueue(E element) {
        if (element == null) throw new NullPointerException("Cannot enqueue null element");
        queue.add(element);
    }
    
     /**
     * Removes and returns the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public E dequeue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
        return queue.remove(0);
    }
    
    /**
     * Retrieves, but does not remove, the element at the front of the queue.
     *
     * @return the element at the front of the queue
     * @throws EmptyQueueException if the queue is empty
     */
    @Override
    public E peek() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException("Queue is empty");
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
    
     /**
     * Compares this queue to another queue for equality.
     *
     * @param that the queue to compare to
     * @return true if both queues are equal, false otherwise
     */
    @Override
    public boolean equals(QueueADT<E> that) {
        if (that == null || that.size() != this.size()) return false;
        // Compare elements of both queues
        Iterator<E> thisIterator = this.iterator();
        Iterator<E> thatIterator = (Iterator<E>) that.iterator();

        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            if (!thisIterator.next().equals(thatIterator.next())) {
                return false;
            }
        }
        return true;
    }
    
     /**
     * Returns an iterator over the elements in the queue.
     *
     * @return an iterator for the queue
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < queue.size();
            }

            @Override
            public E next() {
             
                return queue.get(currentIndex++);
            }
        };
    }
    
     /**
     * Returns an array containing all the elements in the queue.
     *
     * @return an array of elements from the queue
     */
    @Override
    public Object[] toArray() {
        Object[] result = new Object[queue.size()];
        for (int i = 0; i < queue.size(); i++) {
            result[i] = queue.get(i);
        }
        return result;
    }
    
     /**
     * Returns an array containing all the elements in the queue.
     *
     * @param copy the array into which the elements of the queue are to be stored
     * @return an array of elements from the queue
     * @throws NullPointerException if the provided array is null
     */
    @Override
    public E[] toArray(E[] copy) {
        if (copy == null) throw new NullPointerException("The provided array is null");
        if (copy.length < queue.size()) {
            copy = (E[]) java.lang.reflect.Array.newInstance(copy.getClass().getComponentType(), queue.size());
        }
        for (int i = 0; i < queue.size(); i++) {
            copy[i] = queue.get(i);
        }
        return copy;
    }
    
     /**
     * Indicates whether the queue is full.
     *
     * @return false, as this implementation does not have a maximum capacity
     */
    @Override
    public boolean isFull() {
        return false;
    }
}