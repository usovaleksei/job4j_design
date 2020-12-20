package list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Class singly linked list
 * @author Aleksei Usov
 * @since 20/12/2020
 */

public class SimpleLinkedList<E> implements Iterable<E> {

    private Node<E> head;
    private Node<E> tail;
    private int modCount = 0;
    private int nodeCount = 0;

    /**
     * method to adding element to tail singly linked list
     * @param value - adding element
     */

    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        node.item = value;
        if (isEmpty()) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        modCount++;
        nodeCount++;
    }

    /**
     * method to get element from list
     * @param index getting element
     * @return element with index
     */

    public E get(int index) {
        Objects.checkIndex(index, nodeCount);
        Node<E> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        return current.item;
    }

    /**
     * method checking is the list empty
     * @return true if the list empty
     */

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            Node<E> current = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E item = current.item;
                current = current.next;
                return item;
            }
        };
    }

    /**
     * inner class,
     * model of new element for singly list
     * @param <E>
     */

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
