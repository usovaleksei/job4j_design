package statistic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    private List<Analize.User> listBefore = new ArrayList<>();
    private final Analize statistic = new Analize();

    @Before
    public void setUp() {
       listBefore = List.of(new Analize.User(1, "Alex"),
                new Analize.User(2, "Roman"),
                new Analize.User(3, "Sergey"));
    }

    @Test
    public void whenOneAdded() {
        List<Analize.User> listAfter = List.of(new Analize.User(4, "Mihail"),
                new Analize.User(1, "Alex"),
                new Analize.User(2, "Roman"),
                new Analize.User(3, "Sergey"));
        Analize.Info info = new Analize.Info(1, 1, 0);
        assertThat(statistic.diff(listBefore, listAfter).getAdded(), is(info.getAdded()));
    }

    @Test
    public void whenOneAddedThenOneDeleted() {
        List<Analize.User> listAfter = List.of(new Analize.User(4, "Mihail"),
                new Analize.User(2, "Roman"),
                new Analize.User(3, "Sergey"));
        Analize.Info info = new Analize.Info(1, 1, 0);
        assertThat(statistic.diff(listBefore, listAfter).getDeleted(), is(info.getDeleted()));
    }

    @Test
    public void whenOneAddedTwoChanges() {
        List<Analize.User> listAfter = List.of(new Analize.User(4, "Mihail"),
                new Analize.User(1, "Victor"),
                new Analize.User(2, "Roman"),
                new Analize.User(3, "Semen"));
        Analize.Info info = new Analize.Info(1, 0, 2);
        assertThat(statistic.diff(listBefore, listAfter).getChanged(), is(info.getChanged()));
        assertThat(statistic.diff(listBefore, listAfter).getDeleted(), is(info.getDeleted()));
    }

    @Test
    public void whenNoChanges() {
        List<Analize.User> listAfter = List.of(new Analize.User(1, "Alex"),
                new Analize.User(2, "Roman"),
                new Analize.User(3, "Sergey"));
        Analize.Info info = new Analize.Info(0, 0, 0);
        assertThat(statistic.diff(listBefore, listAfter).getChanged(), is(info.getChanged()));
        assertThat(statistic.diff(listBefore, listAfter).getDeleted(), is(info.getDeleted()));
        assertThat(statistic.diff(listBefore, listAfter).getAdded(), is(info.getAdded()));
    }
}
