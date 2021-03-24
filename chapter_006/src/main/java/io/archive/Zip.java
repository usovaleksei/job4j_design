package io.archive;

import io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * class packing directory
 * @author Aleksei Usov
 * @since 13/03/2021
 */

public class Zip {

    /**
     * method packing folder and all files in this folder
     * @param sources folder to packing
     * @param target folder to archive
     */

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream((new BufferedOutputStream(new FileOutputStream(target))))) {
            for (Path file : sources) {
                addToZip(zip, file.toFile());
                }
            } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * method packing single file
     * @param source file name to packing
     * @param target file name to archive
     * @throws IOException if an I/O error has occurred
     */

    public void packSingleFile(File source, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target.getPath())))) {
            addToZip(zip, source);
            }
    }

    /**
     * method write file contents to ZipEntry object
     * @param zip output stream
     * @param source directory name to packing
     * @throws IOException if an I/O error has occurred
     */

    private void addToZip(ZipOutputStream zip, File source) throws IOException {
        zip.putNextEntry(new ZipEntry(source.getPath()));
        try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
            zip.write(out.readAllBytes());
        }
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        if (!argZip.valid()) {
            throw new IllegalArgumentException("Invalid argument");
        }
        Path start = Paths.get(argZip.directory());
        Zip zip = new Zip();
        List<Path> source = Search.search(start, p -> !p.toFile().getName().endsWith(args[3]));
        zip.packFiles(source, new File(argZip.output()));
    }
}
