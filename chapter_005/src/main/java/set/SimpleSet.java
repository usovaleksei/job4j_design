package set;

import list.SimpleArrayList;
import java.util.Iterator;

/**
 * Class simple Set
 * @author Aleksei Usov
 * @since 24/12/2020
 */

public class SimpleSet<E> implements Iterable<E> {

    private final SimpleArrayList<E> set = new SimpleArrayList<>();

    /**
     * method add element to set if it is unique
     * @param e - adding element
     */

    public void add(E e) {
        if (contain(e)) {
            this.set.add(e);
        }
    }

    /**
     * method checking is element is unique in the set
     * @param e cheking element
     * @return false if the element is not unique, true - if the element is unique
     */

    private boolean contain(E e) {
        for (E value : this.set) {
            if (value.equals(e)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }
}
