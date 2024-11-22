package utilities;

import java.util.Iterator;

//Author Nirbhay Vachhani
/**
 * this ADT is a first-in, first-out stack of elements (FIFO)
 * 
 * @param <E> are the types of elements in the stack
 */
public interface QueueADT<E> {

	/**
	 * adds element to top of queue
	 * 
	 * @param element to be added
	 * @throws NullPointerException is passed element is null
	 */
	void enqueue(E element);
	
	/**
	 * removes and returns element at front of queue
	 * 
	 * @return element E that is to be removed
	 */
	E dequeue();
	
	/**
	 * does not remove but returns element at from of queue
	 * 
	 * @return element E at front
	 
	 */
	E peek();
	
	/**
	 * compares current and other queue if equal
	 * 
	 * @param that is the queue to be compared with
	 * @return true if both queues have equal elements and are in the same order, false otherwise
	 */
	boolean equals(QueueADT<E> that);
	
	/**
	 * 
	 * @return an iterator over the items contained in queue
	 */
	Iterator<E> iterator();
	
	/**
	 * 
	 * @return an array containing all of the items in the queue
	 */
	Object[] toArray();
	
	/**
	 * returns an array that has all items in queue, which is in the specified array
	 * 
	 * @param copy is the array where the elements of the queue will be stored
	 * @return an array that has all items in queue
	 */
	E[] toArray(E[] copy);
	
	/**
	 * 
	 * @return true if queue is full, and has a fixed-size, false otherwise
	 */
	boolean isFull();
	
	/**
	 * 
	 * @return the number of elements in the queue
	 */
	int size();
	
	/**
	 * 
	 * @return true if queue is empty, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * removes all elements from queue
	 */
	void dequeueAll();
}
