package io;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class write multiplication table in file
 *
 * @author Aleksei Usov
 * @since 27/02/2021
 */

public class MultiplicationTable {

    public static void main(String[] args) {

        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= 9; i++) {
            result.append(System.lineSeparator()).append("Умножение на ").append(i).append(": ").append(System.lineSeparator());
            for (int j = 0; j <= 10; j++) {
                result.append(i).append(" x ").append(j).append(" = ").append(i * j).append(System.lineSeparator());
            }
        }

        try (FileOutputStream out = new FileOutputStream("Multiplication_table.txt")) {
            out.write(result.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
