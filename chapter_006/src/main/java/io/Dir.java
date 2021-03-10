package io;

import java.io.File;
import java.util.Objects;

/**
 * class get all elements of directory and display their size
 * @author Aleksei Usov
 * @since 10/03/2021
 */

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage java -jar dir.jar ROOT_FOLDER");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        System.out.println(String.format("total size : %s bytes", file.getTotalSpace()));
        for (File subfile : Objects.requireNonNull(file.listFiles())) {
            System.out.printf("%s %d bytes %n", subfile.getName(), subfile.length());
        }
    }
}
