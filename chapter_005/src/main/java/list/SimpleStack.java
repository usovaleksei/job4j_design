package list;

/**
 * Class container Stack with singly linked list
 * @author Aleksei Usov
 * @since 21/12/2020
 */


public class SimpleStack<T> {

    ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * method delete last element from stack
     * @return deleting element
     */

    public T pop() {
        return linked.deleteLast();
    }

    /**
     * method add element to stack
     * @param value adding element
     */
    public void push(T value) {
        linked.add(value);
    }
}
