package io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * class run finder duplicates files in directory
 * @author Aleksei Usov
 * @since 10/03/2021
 */

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Root folder not set");
        }
        Path start = Paths.get(args[0]);
        DuplicatesVisitor visitor = new DuplicatesVisitor();
        Files.walkFileTree(start, visitor);
        visitor.getDuplicates().forEach(p -> System.out.println(p.toString()));
    }
}
