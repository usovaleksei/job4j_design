package io.encoding;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * class demonstration work with encoding
 * @author Aleksei Usov
 * @since 18/03/2021
 */

public class UsageEncoding {

    /**
     * method reading file by path
     * @param path name of path to file
     * @return reading data
     */
    public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(path, Charset.forName("WINDOWS-1251")))) {
            int data;
            while ((data = br.read()) > 0) {
                builder.append((char) data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * method write data to file with determine encoding
     * @param path name of path to file for writing
     * @param data data to write to file
     */

    public void writeDataInFile(String path, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            writer.write(data + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "./data/text.txt";
        UsageEncoding encoding = new UsageEncoding();
        List<String> strings = List.of(
                "Новая строка 1",
                "Новая строка 2",
                "Новая строка 3",
                "Новая строка 4",
                "Новая строка 5"
        );
        for (String str : strings) {
            encoding.writeDataInFile(path, str);
        }
        String s = encoding.readFile(path);
        System.out.println("Данные из файла: ");
        System.out.println(s);
    }
}
