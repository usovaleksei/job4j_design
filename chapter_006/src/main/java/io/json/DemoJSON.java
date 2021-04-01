package io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DemoJSON {
    public static void main(String[] args) {
        final Person person = new Person(false, 35, new Contact("777-77-77"), new String[]{"married", "worker"});

        /*transform object person to json-string*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));

        /*modifier json-string*/
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                            + "{"
                            + "\"phone\":\"+7(924)111-111-11-11\""
                            + "},"
                        + "\"statuses\":"
                            + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
    }
}
