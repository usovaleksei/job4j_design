package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class for delete first element in singly list
 * @author Aleksei Usov
 * @since 20/12/2020
 */

public class ForwardLinked<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;
    private int modCount = 0;

    /**
     * method adding new element
     * @param value adding element;
     */

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        this.tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
        modCount++;
    }

    /**
     * method delete head
     * @return lik to deleting first element
     */

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T temp = head.item;
        head = head.next;
        return temp;
    }

    /**
     * method delete last element from list
     * @return deleting element
     */

    public T deleteLast() {
        Node<T> current = head;
        Node<T> prev = null;
        if (head == null) {
            throw new NoSuchElementException();
        }
        while (current.next != null) {
            prev = current;
            current = current.next;
        }
        T element = current.item;
        if (prev == null) {
            head = null;
            tail = null;
        } else {
            prev.next = null;
        }
        return element;
    }

    /**
     * method revert singly linked list from tail to head
     */

    public void revert() {
        if (head.next != null) {
            tail = head;
            Node<T> current = head.next;
            head.next = null;
            while (current != null) {
               Node<T> next = current.next;
               current.next = head;
               head = current;
               current = next;
            }
        }
    }

    /**
     * iterator for singly list
     * @return object iterator
     */

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    private static class Node<T> {
        T item;
        Node<T> next;

        public Node(T item, Node<T> next) {
            this.item = item;
            this.next = next;
        }
    }
}
