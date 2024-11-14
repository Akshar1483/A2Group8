package implementations;

import java.util.NoSuchElementException;
import utilities.Iterator;
import utilities.ListADT;

public class MyDLL<E> implements ListADT<E> {
    private MyDLLNode<E> head;
    private MyDLLNode<E> tail;
    private int size;

    public MyDLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public boolean add(int index, E element) throws NullPointerException, IndexOutOfBoundsException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null.");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        MyDLLNode<E> newNode = new MyDLLNode<>(element);

        if (index == 0) { 
            if (head == null) { 
                head = tail = newNode; //if list empty, everything is newNode
            } else { 
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode; //if index at head, do head insert
            }
        } else if (index == size) { 
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode; //same for tail
        } else { 
            MyDLLNode<E> current = getNodeAt(index);
            newNode.setNext(current);
            newNode.setPrev(current.getPrev());
            if (current.getPrev() != null) {
                current.getPrev().setNext(newNode);
            } // set (current) to next and prev of new node, reinsert
            current.setPrev(newNode);
        }

        size++;
        return true;
    }

    @Override
    public boolean add(E element) throws NullPointerException {
        return add(size, element);
    }
    
    @Override
    public boolean addAll(ListADT<? extends E> otherList) {
        if (otherList == null) {
            throw new NullPointerException("The list to add cannot be null.");
        }

        for (int i = 0; i < otherList.size(); i++) {
            E element = otherList.get(i);
            add(element); //use the add method to append elements
        }
        return true;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        MyDLLNode<E> node = getNodeAt(index);
        return node.getData();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        MyDLLNode<E> nodeToRemove = getNodeAt(index);
        E data = nodeToRemove.getData();

        if (nodeToRemove == head) { 
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            }
        } else if (nodeToRemove == tail) { 
            tail = tail.getPrev();
            if (tail != null) {
                tail.setNext(null);
            }
        } else { 
            nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
            nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
        }

        if (size == 1) {
            head = tail = null;
        }

        size--;
        return data;
    }
    
    @Override
    public E remove(E toRemove) throws NullPointerException {
        if (toRemove == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        MyDLLNode<E> current = head;

        //traverse the list to find element
        while (current != null) {
            if (current.getData().equals(toRemove)) { //element found
                if (current.getPrev() != null) {
                    current.getPrev().setNext(current.getNext());//link element
                } else {
                    head = current.getNext();
                }
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());//link to prev
                } else {
                    tail = current.getPrev();
                }

                size--;
                return current.getData();
            }
            current = current.getNext();//if element not found, move on
        }
        return null;//element doesnt exist in list
    }
    
    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
        if (element == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        MyDLLNode<E> current = head;

        //traverse to the node at the index
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        E old = current.getData();
        current.setData(element);
        return old;
    }
    
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public boolean contains(E element) throws NullPointerException {
        if (element == null) {
            throw new NullPointerException("Element cannot be null.");
        }

        MyDLLNode<E> current = head;
        while (current != null) {
            if (current.getData().equals(element)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    @Override
    public E[] toArray(E[] toHold) throws NullPointerException {
        if (toHold == null) {
            throw new NullPointerException("Array cannot be null.");
        }

        if (toHold.length < size) {
            toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
        } //idk if this works honestly, please test to confirm

        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            toHold[i++] = current.getData();
            current = current.getNext();
        }
        return toHold;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyDLLNode<E> current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.getData();
            current = current.getNext();
        }
        return array;
    }

    @Override
    public Iterator<E> iterator() {
        return new DLLIterator();
    }

    private MyDLLNode<E> getNodeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }

        MyDLLNode<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    private class DLLIterator implements Iterator<E> {
        private MyDLLNode<E> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
