package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * interface for tree
 * @author Aleksei Usov
 * @since 07/02/2021
 */

public interface SimpleTree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    /**
     * inner class model of element tree
     * @param <E> element type
     */

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }
    }
}
