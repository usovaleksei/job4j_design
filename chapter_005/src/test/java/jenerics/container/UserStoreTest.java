package jenerics.container;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @Test
    public void whenAdd() {
        UserStore userStore = new UserStore();
        userStore.add(new User("1", "Alex"));
        assertThat(userStore.findById("1").getId(), is("1"));
    }

    @Test
    public void whenReplace() {
        UserStore userStore = new UserStore();
        User userOne = new User("1", "Alex");
        User userTwo = new User("2", "Roman");
        userStore.add(userOne);
        userStore.replace("1", userTwo);
        assertThat(userStore.findById("2").getId(), is("2"));
    }

    @Test
    public void whenDelete() {
        UserStore userStore = new UserStore();
        User userOne = new User("1", "Alex");
        User userTwo = new User("2", "Roman");
        userStore.add(userOne);
        userStore.add(userTwo);
        userStore.delete("1");
        assertThat(userStore.findById("2").getId(), is("2"));
    }
}
