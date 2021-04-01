package io.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * class demonstrate modification POJO object to XML with JAXB
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
        final JsonUser jsonUser = new JsonUser(true, 35, "Aleksei", new Adress("Moscow", "Planetnaya", 25, 191), new String[]{"email@mail.ru", "yandex@ya.ru"});

        //Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(JsonUser.class);
        //Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        //Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            //сериализуем
            marshaller.marshal(jsonUser, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        //Для десериализации нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            //десериализуем
            JsonUser result = (JsonUser) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
