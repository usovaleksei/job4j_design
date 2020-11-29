package it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class return arrays element in reverse order
 * @author Aleksei Usov
 * @since 29/11/2020
 */

public class BackwardArrayIt implements Iterator<Integer> {

    private final int[] data;
    private int point = 0;

    public BackwardArrayIt(int[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return point < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[data.length - 1 - point++];
    }
}
