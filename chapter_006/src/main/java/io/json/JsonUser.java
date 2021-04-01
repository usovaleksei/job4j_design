package io.json;

import java.util.Arrays;

public class JsonUser {
    private final boolean sex;
    private final int age;
    private final String name;
    private final Adress adress;
    private final String[] mails;

    public JsonUser(boolean sex, int age, String name, Adress adress, String[] mails) {
        this.sex = sex;
        this.age = age;
        this.name = name;
        this.adress = adress;
        this.mails = mails;
    }

    @Override
    public String toString() {
        return "User{" +
                "sex=" + sex +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", adress=" + adress +
                ", mails=" + Arrays.toString(mails) +
                '}';
    }
}
