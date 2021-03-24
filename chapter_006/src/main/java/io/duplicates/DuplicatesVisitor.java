package io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * class find duplicates to directory
 *
 * @author Aleksei Usov
 * @since 10/03/2021
 */

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final Set<FileProperty> uniqueFiles = new HashSet<>();
    private final List<FileProperty> duplicateFiles = new ArrayList<>();

    /**
     * method find duplicated file from directory
     * @param file path to directory
     * @param attrs attributes
     * @return all files in all subdirectory
     * @throws IOException exception I/O
     */

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().getName(), file.toFile().length());
        if (this.uniqueFiles.contains(fileProperty)) {
            this.duplicateFiles.add(fileProperty);
        } else {
            this.uniqueFiles.add(fileProperty);
        }
        return FileVisitResult.CONTINUE;
    }

    /**
     * method get list of duplicated files
     * @return list of duplicated files
     */

    public List<FileProperty> getDuplicates() {
        return this.duplicateFiles;
    }
}
