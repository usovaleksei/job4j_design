package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * class reading config file
 * @author Aleksei Usov
 * @since 09/03/2021
 */

public class Config {

    private final String path;
    private final Map<String, String> values = new LinkedHashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * method put all keys from config file to map
     */

    public void load() {
        this.values.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.lines().
                    filter(line -> !line.isEmpty() && !line.startsWith("#")).
                    forEach(s -> {
                        String[] strings = s.split("=");
                        if (strings.length <= 1) {
                            throw new IllegalArgumentException();
                        }
                        this.values.put(strings[0], strings[1]);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method return value from map by key
     * @param key key
     * @return value from map
     */

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("./chapter_006/src/main/resources/app.properties"));
    }
}
