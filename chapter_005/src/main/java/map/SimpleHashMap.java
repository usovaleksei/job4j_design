package map;

import java.util.*;

/**
 * Class singly hash map
 * @author Aleksei Usov
 * @since 04/02/20212
 */

public class SimpleHashMap<K, V> implements Iterable<SimpleHashMap.Node<K,V>> {
    private Node<K, V>[] table;
    private static final int INITIAL_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;
    private int modCount = 0;
    private int size = 0;

    public SimpleHashMap() {
        this.table = new Node[INITIAL_CAPACITY];
    }

    /**
     * method adding new element to map
     * @param key - object key
     * @param value - object value
     * @return - true, if element was adding; false, if element not adding
     */

    public boolean insert(K key, V value) {
        boolean result = false;
        checkSizeTable();
        int hash = hash(key);
        int indexBucket = indexBucket(hash);
        if (this.table[indexBucket] == null) {
                Node<K, V> node = new Node<>(hash, key, value);
                this.table[indexBucket(hash)] = node;
                result = true;
                this.size++;
                this.modCount++;
            }
        return result;
    }

    /**
     * method delete element from table by key
     * @param key element key
     * @return true if delete was success, false if element not found
     */

    public boolean delete(K key) {
        boolean result = false;
        Node<K, V> node = this.table[indexBucket(hash(key))];
        if (node != null && (node.getKey() == key)) {
            table[indexBucket(hash(key))] = null;
            result = true;
        }
        this.size--;
        this.modCount--;
        return result;
    }

    public V get(K key) {
        V result = null;
        int hash = hash(key);
        Node<K, V> node = this.table[indexBucket(hash)];
        if (node != null && (node.getKey().equals(key))) {
            result = node.getValue();
        }
        return result;
    }

    /**
     * method check table size and resize if necessary
     */

    public void checkSizeTable() {
        if (size >= this.table.length * LOAD_FACTOR) {
            grow();
        }
    }

    /**
     * method doubles the size of the array table
     */

    public void grow () {
        Node<K, V>[] oldTable = this.table;
        this.modCount++;
        this.table = new Node[oldTable.length * 2 + 1];
        this.size = 0;
        for (Node<K, V> node : oldTable) {
            if (node != null) {
                insert(node.getKey(), node.getValue());
            }
        }
    }

    /**
     * method defines index of table
     * @param hash - key hash code
     * @return index to array for element
     */

    public int indexBucket(int hash) {
        return (this.table.length - 1) & hash;
    }

    public int hash(K key) {
        int h = key.hashCode();
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    /**
     * method calculates map capacity
     * @return map capacity
     */

    public int capacity() {
        return this.table.length;
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<>() {

            private int cursor = 0;
            private int position = 0;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return cursor < size;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = null;
                for (int index = position; index < table.length; index++) {
                    if (table[index] != null) {
                        result = table[index];
                        cursor++;
                        position = ++index;
                        break;
                    }
                }
                return result;
            }
        };
    }

    /**
     * inner class,
     * model of new element for hash table
     */

    protected static class Node<K, V> {
        private final int hash;
        private final K key;
        private V value;

        public Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(key, node.key) && Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + key.hashCode();
            return result;
        }
    }
}
