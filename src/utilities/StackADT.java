package utilities;

import java.util.Iterator;

//Author Nirbhay Vachhani
/**
 * this ADT is a last-in, first-out stack of elements (LIFO)
 * 
 * @param <E> are the types of elements in the stack
 */
public interface StackADT<E> {

	/**
	 * adds element to top of stack
	 * 
	 * @param element to be added
	 * @throws NullPointerException if passed element is null
	 */
	void push(E element);
	
	/**
	 * removes and returns element at top of stack
	 * 
	 * @return element E that is to be removed
	 */
	E pop();
	
	/**
	 * does not remove but returns elemet at top of stack
	 * 
	 * @return element E at top
	 */
	E peek();
	
	/**
	 * compares current and other stack if equal
	 * 
	 * @param that is the stack to be compared with
	 * @return true if both stacks have equal elements and are in the same order, false otherwise
	 */
	boolean equals(StackADT<E> that);
	
	/**
	 * 
	 * @return an iterator over the items contained in stack
	 */
	Iterator<E> iterator();
	
	/**
	 * 
	 * @return an array containing all of the items in the stack
	 */
	Object[] toArray();
	
	/**
	 * returns an array that has all items in stack, which is in the specified array
	 * 
	 * @param copy is the array where the elements of the stack will be stored
	 * @return an array that has all items in stack
	 */
	E[] toArray(E[] copy);
	
	/**
	 * searches for element in stack that is passed in parameter
	 * 
	 * @param obj is the element to search for
	 * @return the position of the element, 1 is the top. -1 if not found
	 */
	int search(E obj);
	
	/**
	 * checks if stack contains element passed in parameter
	 * 
	 * @param obj is the element to search for
	 * @return true if stack contains element, false otherwise
	 */
	boolean contains(E obj);
	
	/**
	 * 
	 * @return the number of elements in the stack
	 */
	int size();
	
	/**
	 * 
	 * @return true if stack is empty, false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * removes all elements from stack
	 */
	void clear();
}
