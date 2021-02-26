package inout;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * class write multiplication table in file
 * @author Aleksei Usov
 * @since 26/02/2021
 */

public class MultiplicationTable {

    public static void main(String[] args) {

        try (FileOutputStream out = new FileOutputStream("multiplication_table.txt")) {
            for (int i = 1; i <= 9; i++) {
                String topString = "\n" + "Умножение на " + i + ": " + "\n";
                out.write(topString.getBytes());
                for (int j = 0; j <= 10; j++) {
                    String result = i + " x " + j + " = " + i * j + "\n";
                    out.write(result.getBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

