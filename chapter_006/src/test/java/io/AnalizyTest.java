package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenReadThenWriteLog() throws IOException {
        Analizy log = new Analizy();
        File source = new File("serverlog.txt");
        File target = new File("unavailable.csv");
        try (PrintWriter out = new PrintWriter(source)) {
            out.println("200 10:56:01" + System.lineSeparator());
            out.println("500 10:57:01" + System.lineSeparator());
            out.println("400 10:58:01" + System.lineSeparator());
            out.println("200 10:59:01" + System.lineSeparator());
            out.println("500 11:01:02" + System.lineSeparator());
            out.println("200 11:02:02");
        }
        log.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target.getAbsolutePath()))) {
            in.lines().forEach(rsl::append);
        }
        String expected = ("10:57:01;10:59:01" + "11:01:02;11:02:02");
        assertThat(rsl.toString(), is(expected));
    }
}
