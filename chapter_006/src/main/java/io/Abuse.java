package io;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * class delete prohibited words from file
 * @author Aleksei Usov
 * @since 09/03/2021
 */

public class Abuse {

    /**
     * method deleted prohibited words from file
     * @param source name of original file
     * @param target name of final file
     * @param words list of stop words
     * @throws IOException exception IO
     */

    public static void drop(String source, String target, List<String> words) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            in.lines()
                    .flatMap(line -> Stream.of(line.split("\\s+")))
                    .filter(word -> !words.contains(word))
                    .map(word -> word + " ")
                    .forEach(out::print);
        }
    }
}
