package list;

import java.util.NoSuchElementException;

/**
 * Class queue from two stacks
 * @author Aleksei Usov
 * @since 21/12/2020
 */

public class SimpleQueue<T> {

    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * method return first element and delete this from queue
     * @return first element of queue
     */

    public T poll() {
        if (this.in.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (this.out.isEmpty()) {
            while (!this.in.isEmpty()) {
                this.out.push(this.in.pop());
            }
        }
        T temp = null;
        if (!this.out.isEmpty()) {
            temp = this.out.pop();
        }
        return temp;
    }

    /**
     * method add element to tail of queue
     * @param value adding element
     */

    public void push(T value) {
        this.in.push(value);
    }
}
