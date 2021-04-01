package io.json;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class demonstrate modification POJO object to JSON and back
 *
 * @author Aleksei Usov
 * @since 01/04/2021
 */

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class JsonUser {

    @XmlAttribute
    private boolean sex;
    private int age;
    private String name;
    private Adress adress;

    @XmlElementWrapper
    @XmlElement(name = "mail")
    private String[] mails;

    public JsonUser() {

    }

    public JsonUser(boolean sex, int age, String name, Adress adress, String[] mails) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.adress = adress;
        this.mails = mails;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Adress getAdress() {
        return adress;
    }

    public String[] getMails() {
        return mails;
    }

    @Override
    public String toString() {
        return "User{"
                + "sex=" + sex
                + ", age=" + age
                + ", name='" + name + '\''
                + ", adress=" + adress
                + ", mails=" + Arrays.toString(mails)
                + '}';
    }

    public static void main(String[] args) throws JAXBException, IOException {

        /*JSONObject из json-строки*/
        JSONObject jsonAddress = new JSONObject("{\"address\":{\"city\":\"Moscow\",\"street\":\"Planetnaya\",\"house\":25,\"flat\":191}}");
        System.out.println("JSONObject from json-string: " + jsonAddress.toString());

        /*JSONArray из ArrayList*/
        List<String> list = new ArrayList<>();
        list.add("email@mail.ru");
        list.add("yandex@ya.ru");
        JSONArray jsonMails = new JSONArray(list);
        System.out.println("JSONObject from List: " + jsonMails.toString());

        /*JSONObject напрямую методом put*/
        final JsonUser jsonUser = new JsonUser(true, 35, "Aleksei", new Adress("Moscow", "Planetnaya", 25, 191), new String[]{"email@mail.ru", "yandex@ya.ru"});
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", jsonUser.isSex());
        jsonObject.put("age", jsonUser.getAge());
        jsonObject.put("name", jsonUser.getName());
        jsonObject.put("address", jsonUser.getAdress());
        jsonObject.put("mails", jsonUser.getMails());

        System.out.println("JSONObject with put-method: " + jsonObject.toString());

        /*Преобразование объекта jsonUser в json-строку*/
        System.out.println("json-string from JSONObject: " + new JSONObject(jsonObject.toString()));
    }
}
