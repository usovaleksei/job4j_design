package jenerics;

import java.util.*;

/**
 * Class simple array - a universal wrapper over an array
 * @author Aleksei Usov
 * @since 15/12/2020
 */

public class SimpleArray<T> implements Iterable<T> {

    private Object[] array;
    private int position = 0;

    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * method element to array
     * @param model - adding element
     */

    public void add(T model) {
        this.array[position++] = model;
    }

    /**
     * method replace element
     * @param index to replace element
     * @param model substitute element
     */

    public void set(int index, T model) {
        Objects.checkIndex(index, position);
        this.array[index] = model;
    }

    /**
     * method remove element from array
     * @param index removed element
     */

    public void remove(int index) {
        Objects.checkIndex(index, position);
        System.arraycopy(this.array, index + 1, this.array, index, this.position - index);
        this.array[position - 1] = null;
        position--;
    }

    /**
     * method to get element from index
     * @param index of element
     * @return element
     */

    @SuppressWarnings("unchecked")
    public T get(int index) {
        Objects.checkIndex(index, position);
        return (T) this.array[index];
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < position;
            }

            @Override
            @SuppressWarnings("unchecked")
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[count++];
            }
        };
    }
}
