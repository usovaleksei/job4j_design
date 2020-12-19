package list;

import java.util.*;

/**
 * Class simple ArrayList
 * @author Aleksei Usov
 * @since 18/12/2020
 */

public class SimpleArrayList<T> implements Iterable<T> {

    private Object[] container;
    private int position = 0;
    private int modCount = 0;

    public SimpleArrayList() {
        this.container = new Object[0];
    }

    /**
     * method get element for index
     * @param index of element
     * @return element
     */

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.container[index];
    }

    /**
     * method adding new element to array Object[]
     * @param element adding element
     */

    public void add(T element) {
        if (position >= this.container.length) {
            grow();
        }
        this.container[position] = element;
        position++;
        modCount++;
    }


    /**
     * doubles the size of the array
     */
    public void grow() {
        this.container = Arrays.copyOf(this.container, position * 2 + 1);
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<>() {

            private int count = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {

                //checks if the collection has changed since the iterator call
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < position;
            }

            @SuppressWarnings("unchecked")
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) container[count++];
            }
        };
    }
}
