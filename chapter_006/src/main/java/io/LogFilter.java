package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class show lines from file by condition
 *
 * @author Alexei Usov
 * @since 27/02/2021
 */

public class LogFilter {

    /**
     * method filtered lines from file by condition
     *
     * @param file file to analyze
     * @return list of lines where condition is true
     */

    public static List<String> filter(String file) {
        List<String> filtered = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            filtered = in.lines().filter(line -> line.contains("404")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filtered;
    }

    /**
     * method save filtered lines to file
     *
     * @param log  list with filtered lines
     * @param file where saved lines
     */

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            log.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        List<String> log = filter("io_files/log.txt");
        save(log, "io_files/filtered.txt");
    }
}
