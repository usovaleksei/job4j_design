package tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

/**
 * class simple tree
 * @author Aleksei Usov
 * @since 07/02/2021
 */

public class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    public Tree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * method add element to tree
      * @param parent parent
     * @param child child
     * @return true if adding was successful, false if child already there is into tree
     */

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentNode = findBy(parent);
        if (parentNode.isPresent() && !findBy(child).isPresent()) {
            parentNode.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * method find element from tree
     * @param value value for searching
     * @return element
     */

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
