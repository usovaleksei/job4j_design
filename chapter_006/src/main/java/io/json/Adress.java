package io.json;

public class Adress {
    private final String city;
    private final String street;
    private final int house;
    private final int flat;

    public Adress(String city, String street, int house, int flat) {
        this.city = city;
        this.street = street;
        this.house = house;
        this.flat = flat;
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
