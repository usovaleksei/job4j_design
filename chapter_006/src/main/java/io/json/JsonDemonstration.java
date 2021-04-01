package io.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * class demonstrate modification object to json and back
 * @author Aleksei Usov
 * @since 01/04/2021
 */

public class JsonDemonstration {
    public static void main(String[] args) {
        final JsonUser jsonUser = new JsonUser(true, 35, "Aleksei", new Adress("Moscow", "Planetnaya", 25, 191), new String[]{"email@mail.ru", "yandex@ya.ru"});

        /*transform object to json-string*/
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(jsonUser));

        /*transform json-string to object*/
        final String userJson =
                "{"
                    + "\"sex\":true,"
                    + "\"age\":35,"
                    + "\"name\":\"Aleksei\","
                    + "\"adress\":"
                        + "{"
                        + "\"city\":\"Moscow\","
                        + "\"street\":\"Planetnaya\","
                        + "\"house\":25,"
                        + "\"flat\":191"
                        + "},"
                    + "\"mails\":"
                        + "[\"email@mail.ru\",\"yandex@ya.ru\"]"
                + "}";
        final JsonUser jsonUserMod = gson.fromJson(userJson, JsonUser.class);
        System.out.println(jsonUserMod);
    }
}
