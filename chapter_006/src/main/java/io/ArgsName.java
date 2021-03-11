package io;

import java.util.HashMap;
import java.util.Map;

/**
 * class reading arguments and put them into the map
 *
 * @author Aleksei Usov
 * @since 11/03/2021
 */

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    /**
     * method get value from map by key
     *
     * @param key key of argument
     * @return value of argument
     */

    public String get(String key) {
        return values.get(key);
    }

    /**
     * method reading arguments and breaks them by pair "key-value"
     * then put into the map
     *
     * @param args arguments for run application
     */

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        for (String argument : args) {
            String[] pair = argument.split("=");
            if (pair.length < 2) {
                throw new IllegalArgumentException("Invalid argument");
            }
            values.put(pair[0].substring(1), pair[1]);
        }
    }

    /**
     * method takes array of arguments
     *
     * @param args arguments
     * @return arguments of ArgsName type
     */

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
