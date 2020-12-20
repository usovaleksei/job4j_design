package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {

    @Test
    public void whenAddThenGet() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.add("second");
        String rsl = list.get(1);
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.add("second");
        Iterator<String> it = list.iterator();
        String rsl = it.next();
        assertThat(rsl, is("first"));
        String rslOne = it.next();
        assertThat(rslOne, is("second"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        list.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleLinkedList<String> list = new SimpleLinkedList<>();
        list.add("first");
        Iterator<String> it = list.iterator();
        list.add("second");
        it.next();
    }
}
