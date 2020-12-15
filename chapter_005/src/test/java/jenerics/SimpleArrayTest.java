package jenerics;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenSet() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        array.set(0, "Replace element");
        String rsl = array.get(0);
        assertThat(rsl, is("Replace element"));
    }

    @Test
    public void whenAddThenRemove() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        array.add("second");
        array.remove(0);
        String rsl = array.get(0);
        assertThat(rsl, is("second"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.iterator().next();
    }
}
