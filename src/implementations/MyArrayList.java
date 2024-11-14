package implementations;

import java.util.Arrays;
import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

//Author: Martin Davis
//This class implements the provided ListADT and makes use of Iterator.java.

public class MyArrayList<E> implements ListADT<E> {

	private static final int DEFAULT_CAP = 10; //array must be initialized with a cap, so I made the default 10
	private E[] elements;
	private int size;
	
	//makes empty list with default cap of 10
	public MyArrayList() {
		this.elements = (E[]) new Object[DEFAULT_CAP];
		this.size = 0;
	}
	
	//if list is given a size
	public MyArrayList(int initialCap) {
		if (initialCap < 0) throw new IllegalArgumentException("Max capacity should be positve."); 
		this.elements = (E[]) new Object[initialCap]; //passed cap 
		this.size = 0;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void clear() {
		for(int i=0; i<size; i++) {
			elements[i] = null;
		}
		size = 0;//reset
	}
	
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException {
		if (toAdd == null) 
			throw new NullPointerException("Element is null.");
		if (index < 0 || index > size) 
			throw new IndexOutOfBoundsException("Index out of bounds.");
		
		ensureCapacity(size + 1); 
		//this is a custom method inspired by the method in the ArrayList class
		//increases the capacity of the list as needed.
		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = toAdd;
		size++;
		return true;
	}
	
	@Override
    public boolean add(E toAdd) throws NullPointerException {
        if (toAdd == null) 
        	throw new NullPointerException("Element is null.");
        
        ensureCapacity(size + 1);
        elements[size++] = toAdd;
        return true;
    }
	
	@Override
    public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException {
        if (toAdd == null) 
        	throw new NullPointerException("List to add is null.");
        
        ensureCapacity(size + toAdd.size());

        //use iterator to add each element casting to E
        Iterator<? extends E> it = toAdd.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
        return true;
    }
	
	@Override
	public E get( int index ) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size) 
			throw new IndexOutOfBoundsException("Index out of bounds.");
        return elements[index];
	}
	
	@Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) 
        	throw new IndexOutOfBoundsException("Index out of bounds.");
        
        E removed = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);//this shifts elements to left
        elements[size] = null;
        size--;
        return removed;
    }
	
	@Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) 
        	throw new NullPointerException("Element is null.");

        for (int i = 0; i < size; i++) {
            if (toRemove.equals(elements[i])) {
                return remove(i); //will cut at first occurence.
            }
        }
        return null;
    }
	
	@Override
    public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException {
        if (toChange == null) 
        	throw new NullPointerException("Element is null.");
        if (index < 0 || index >= size) 
        	throw new IndexOutOfBoundsException("Index out of bounds.");

        E old = elements[index];
        elements[index] = toChange;
        return old;
    }
	
	@Override
    public boolean isEmpty() {
        return size == 0;
    }
	
	@Override
    public boolean contains(E toFind) throws NullPointerException {
        if (toFind == null) 
        	throw new NullPointerException("Element is null.");

        for (int i = 0; i < size; i++) {
            if (toFind.equals(elements[i])) {
                return true;
            }
        }
        return false;
    }
	
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException {
	    if (toHold == null) {
	        throw new NullPointerException("Array cannot be null.");
	    }

	    if (toHold.length < size) {
	        return Arrays.copyOf(elements, size);
	    }

	    System.arraycopy(elements, 0, toHold, 0, size);
	    
	    //set next position to null if array given is longer
	    if (toHold.length > size) {
	        toHold[size] = null;
	    }
	    
	    return toHold;
	}

	
	@Override
    public Object[] toArray() {
        return java.util.Arrays.copyOf(elements, size);
    }
	
	@Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }
	
	/*
	 * this method is a custom method inspired by the one with the same name
	 * in the ArrayList class. It makes sure that the array has enough capacity
	 * if not it will resize the array.
	 */
	private void ensureCapacity(int minCap) {
	    if (minCap > elements.length) {
	        int newCap = Math.max(minCap, elements.length + (elements.length / 2));
	        //checks if passed minCap is bigger than adding 50% to arraylist
	        E[] newArray = (E[]) new Object[newCap];
	        System.arraycopy(elements, 0, newArray, 0, size);
	        elements = newArray;
	    }
	}
	
	//custom iterator for MyArrayList class
	private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) 
            	throw new NoSuchElementException("No more elements in the list.");
            return elements[currentIndex++];
        }
    }
}
