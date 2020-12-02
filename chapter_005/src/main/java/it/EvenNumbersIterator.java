package it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class iterator even numbers
 * @author Aleksei Usov
 * @since 02/12/2020
 */

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] numbers;
    private int position = 0;

    public EvenNumbersIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        for (; position < numbers.length; position++) {
            if (numbers[position] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return numbers[position++];
    }
}
