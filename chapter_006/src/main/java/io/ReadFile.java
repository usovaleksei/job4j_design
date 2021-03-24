package io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class to read file
 * @author Alexei Usov
 * @since 27/02/2021
 */

public class ReadFile {

    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("io_files/input.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
