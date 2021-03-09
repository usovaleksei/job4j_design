package io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {

    @Test
    public void whenReadThenWriteLog() throws IOException {
        Analizy log = new Analizy();
        String source = "./data/serverlog.txt";
        String target = "./data/unavailable.csv";
        String expected = "10:57:01;10:59:01";
        String rsl;
        log.unavailable(source, target);
        try (BufferedReader reader = new BufferedReader(new FileReader(target))) {
            rsl = reader.readLine();
        }
        assertThat(expected, is(rsl));
    }
}
