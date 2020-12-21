package list;

/**
 * Class container Stack with singly linked list
 * @author Aleksei Usov
 * @since 21/12/2020
 */


public class SimpleStack<T> {

    ForwardLinked<T> linked = new ForwardLinked<>();
    private int size = 0;

    /**
     * method delete last element from stack
     * @return deleting element
     */

    public T pop() {
        size--;
        return linked.deleteLast();
    }

    /**
     * method add element to stack
     * @param value adding element
     */
    public void push(T value) {
        linked.add(value);
        size++;
    }

    /**
     * method checking is stack empty
     * @return true if stack is empty
     */

    public boolean isEmpty() {
        return size == 0;
    }
}
