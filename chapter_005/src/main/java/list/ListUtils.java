package list;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Class shows opportunities ListIterator
 * @author Aleksei Usov
 * @since 22/12/2020
 */

public class ListUtils {

    /**
     * method add element to list before established index
     * @param list with elements
     * @param index of element
     * @param value element for adding
     * @param <T> element type
     */

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * method add element to list after established index
     * @param list with elements
     * @param index of element
     * @param value element for adding
     * @param <T> element type
     */

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            if (i.nextIndex() == index) {
                i.next();
                i.add(value);
                break;
            }
            i.next();
        }
    }

    /**
     * Method delete element if the condition is met
     * @param list list with elements
     * @param filter condition
     * @param <T> element type
     * @return new list
     */

    public static <T> List<T> removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
           T element = i.next();
           if (filter.test(element)) {
               i.remove();
           }
        }
        return list;
    }

    /**
     * Method delete element if the condition is met
     * @param list list with elements
     * @param filter condition
     * @param value the element with which we replace
     * @param <T> element type
     * @return new list
     */

    public static <T> List<T> replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            T element = i.next();
            if (filter.test(element)) {
                i.set(value);
            }
        }
        return list;
    }

    /**
     * Method delete all values from another list
     * @param list with element
     * @param elements list with elements for deleting
     * @param <T> element type
     * @return new list without deleting values
     */

    public static <T> List<T> removeAll(List<T> list, List<T> elements) {
        ListIterator<T> i = list.listIterator();
        while (i.hasNext()) {
            T element = i.next();
            if (elements.contains(element)) {
                i.remove();
            }
        }
        return list;
    }
}
