package map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleHashMapTest {

    private SimpleHashMap<String, Integer> map;

    @Before
    public void init() {
        map = new SimpleHashMap<>();
        map.insert("Alex", 35);
        map.insert("Roman", 36);
        map.insert("Sergey", 29);
    }

    @Test
    public void whenInsertUniqueUser() {
        assertThat(map.insert("Petr", 32), is(true));
    }

    @Test
    public void whenInsertEqualsUser() {
        assertThat(map.insert("Roman", 36), is(false));
    }

    @Test
    public void whenDeleteExistingUser() {
        assertThat(map.delete("Sergey"), is(true));
    }

    @Test
    public void whenDeleteUnExistingUser() {
        assertThat(map.delete("Grisha"), is(false));
    }

    @Test
    public void whenGetUser() {
        assertThat(map.get("Alex"), is(35));
        assertNull(map.get("Misha"));
    }

    @Test
    public void whenGrowTable() {
        map.grow();
        assertThat(map.capacity(), is(33));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        Iterator<SimpleHashMap.Node<String, Integer>> it = map.iterator();
        map.insert("Misha", 28);
        it.next();
    }
}
