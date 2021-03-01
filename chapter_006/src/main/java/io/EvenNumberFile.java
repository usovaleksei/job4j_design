package io;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Class read file and show even numbers
 *
 * @author Alexei Usov
 * @since 27/02/2021
 */

public class EvenNumberFile {

    public static void main(String[] args) {

        try (FileInputStream in = new FileInputStream("io_files/even.txt")) {
            StringBuilder result = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                result.append((char) read);
            }

            String[] lines = result.toString().split(System.lineSeparator());

            for (String str : lines) {
                int rsl = Integer.parseInt(str);
                if (rsl % 2 != 0) {
                    System.out.println(str + " - нечетное число");
                } else {
                    System.out.println(str + " - четное число");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}