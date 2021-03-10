package io;

import org.junit.Test;

import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = Objects.requireNonNull(Config.class.getClassLoader().getResource("app.properties")).getFile();
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksei Usov"));
    }

    @Test
    public void whenPairWithComment() {
        String path = Objects.requireNonNull(Config.class.getClassLoader().getResource("app_comment.properties")).getFile();
        Config config = new Config(path);
        config.load();
        assertThat(config.value("keyOne"), is("valueOne"));
        assertThat(config.value("keyTwo"), is("valueTwo"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFileNotCorrect() {
        String path = Objects.requireNonNull(Config.class.getClassLoader().getResource("app_error.properties")).getFile();
        Config config = new Config(path);
        config.load();
    }
}
