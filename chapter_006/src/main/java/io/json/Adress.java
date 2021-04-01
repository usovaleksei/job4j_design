package io.json;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlAttribute;

@XmlElement(value = "address")
public class Adress {

    @XmlAttribute
    private String city;
    private String street;
    private int house;
    private int flat;

    public Adress() {

    }

    public Adress(String city, String street, int house, int flat) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getFlat() {
        return flat;
    }

    @Override
    public String toString() {
        return "Adress{"
                + "city='" + city + '\''
                + ", street='" + street + '\''
                + ", house=" + house
                + ", flat=" + flat
                + '}';
    }
}
