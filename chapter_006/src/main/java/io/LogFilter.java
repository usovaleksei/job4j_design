package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class show lines from file by condition
 * @author Alexei Usov
 * @since 27/02/2021
 */

public class LogFilter {

    /**
     * method filtered lines from file by condition
     * @param file file to analyze
     * @return list of lines where condition is true
     */

    public static List<String> filter(String file) {
        List<String> filtered = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            in.lines().forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            String[] splited = line.split(" ");
            if (splited[splited.length - 2].equals("404")) {
                filtered.add(line);
            }
        }

        return filtered;
    }

    public static void main(String[] args) {

        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
