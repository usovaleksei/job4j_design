package it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class iterator for two-dimensional array
 * @author Aleksei Usov
 * @since 02/12/2020
 */

public class MatrixIt implements Iterator<Integer> {

    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (row < data.length && data[row].length == 0) {
            row++;
        }
        return row < data.length && column < data[row].length;
    }


    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][column++];
        if (column >= data[row].length) {
            row++;
            column = 0;
        }
        return rsl;
    }
}
