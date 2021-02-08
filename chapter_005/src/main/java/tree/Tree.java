package tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

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
     *
     * @param parent parent
     * @param child  child
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
        return findByPredicate(p -> p.value.equals(value));
    }

    /**
     * method checked tree to binarity
     * @return true if tree is binary
     */

    public boolean isBinary() {
        return findByPredicate(p -> p.children.size() > 2).isEmpty();
    }

    /**
     * method find elements by condition
     * @param condition for find element
     * @return elements
     */

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
