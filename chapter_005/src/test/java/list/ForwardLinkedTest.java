package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ForwardLinkedTest {

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteFirst() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.deleteFirst();
        linked.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteEmptyLinked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteFirst();
    }

    @Test
    public void whenMultiDelete() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        assertThat(linked.deleteFirst(), is(1));
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        Iterator<Integer> it = linked.iterator();
        linked.add(2);
        it.next();
    }

    @Test
    public void whenDeleteLast() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Integer rsl = linked.deleteLast();
        assertThat(rsl, is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenDeleteLastEmptyLiked() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.deleteLast();
    }

    @Test
    public void whenAddThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddThenRevertThenIter() {
        ForwardLinked<Integer> linked = new ForwardLinked<>();
        linked.add(1);
        linked.add(2);
        linked.revert();
        Iterator<Integer> it = linked.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
    }
}
