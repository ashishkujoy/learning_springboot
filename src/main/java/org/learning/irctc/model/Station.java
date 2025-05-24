package org.learning.irctc.model;

import java.util.Objects;

public class Station {
    private final String name;
    private final String pinCode;

    public Station(String name, String pinCode) {
        this.name = name;
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public String getPinCode() {
        return pinCode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Station station = (Station) object;
        return Objects.equals(name, station.name) && Objects.equals(pinCode, station.pinCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, pinCode);
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}
