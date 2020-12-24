package set;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    @Test
    public void whenAddAndIter() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.add(1);
        set.add(1);
        set.add(3);
        set.add(2);
        Iterator<Integer> it = set.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenEmptySet() {
        SimpleSet<Integer> set = new SimpleSet<>();
        set.iterator().next();
    }
}
