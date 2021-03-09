package io;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Aleksei Usov"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/app_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("keyOne"), is("valueOne"));
        assertThat(config.value("keyTwo"), is("valueTwo"));
    }
}
