package implementations;

import utilities.StackADT;


/**
 * Stack (LIFO - Last In, First Out) of elements, using `MyArrayList`
 *
 * @param <E> the type of elements held in this stack
 */
public class MyStack<E> implements StackADT<E> {
    private MyArrayList<E> stack;

    /**
     * Constructs an empty stack.
     */
    public MyStack() {
        stack = new MyArrayList<>();
    }

    /**
     * Adds an element to the top of the stack.
     *
     * @param element the element to be added
     * @throws NullPointerException if the passed element is null
     */
    @Override
    public void push(E element) {
        stack.add(element);
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return the element removed from the top of the stack, or null if the stack is empty
     */
    @Override
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return the element at the top of the stack, or null if the stack is empty
     */
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack.get(stack.size() - 1);
    }

    /**
     * Checks if the stack is empty.
     *
     * @return true if the stack is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /**
     * Returns the number of elements in the stack.
     *
     * @return the size of the stack
     */
    @Override
    public int size() {
        return stack.size();
    }

    /**
     * Removes all elements from the stack.
     */
    @Override
    public void clear() {
        stack.clear();
    }
}
