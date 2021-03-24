package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class server availability analysis
 * @author Aleksei Usov
 * @since 09/03/2021
 */

public class Analizy {

    /**
     * method read file source with log and defines time when server not worked
     * @param source name of file source with log
     * @param target name of file after analysis
     */

    public void unavailable(String source, String target) {
        int count = 0;
        List<String> list = new ArrayList<>();
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String s = in.readLine();
            while (s != null) {
                if (count == 0 && (s.contains("400") || s.contains("500"))) {
                    text.append(s.split(" ")[1]).append(";");
                    count++;
                }
                if (count == 1 && (s.contains("200") || s.contains("300"))) {
                    text.append(s.split(" ")[1]).append(System.lineSeparator());
                    count = 0;
                    list.add(text.toString());
                    text.delete(0, text.length());
                }
                s = in.readLine();
            }
            writeLog(target, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method write time where server not worked
     * @param path name of file where were write result
     * @param list list of intervals when server not worked
     */

    public static void writeLog(String path, List<String> list) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(path))) {
            list.forEach(out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy log = new Analizy();
        log.unavailable("./data/serverlog.txt", "./data/unavailable.csv");
    }
}
