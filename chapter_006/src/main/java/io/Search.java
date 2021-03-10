package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * class searching files with condition
 * @author Aleksei Usov
 * @since 10/03/2021
 */

public class Search {

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalStateException("Root folder or etx not set. Usage java -jar dir.jar ROOT_FOLDER ETX");
        }
        Path start = Paths.get(args[0]);
        search(start, args[1]).forEach(System.out::println);
    }

    /**
     * method searching files with specific extension
     * @param root root folder
     * @param ext extension of searching files
     * @return list of files with ext
     * @throws IOException io
     */

    public static List<Path> search(Path root, String ext) throws IOException {
        SearchFiles searcher = new SearchFiles(p -> p.toFile().getName().endsWith(ext));
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
