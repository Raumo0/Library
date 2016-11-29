package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Entity;

import java.util.LinkedList;

/**
 * Created by raumo0 on 15.11.16.
 */
public class City extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private Country country;
    private LinkedList<Address> addresses;

    public City() {}

    public City(City city){
        super(city);
        this.name = city.getName();
        this.country = city.getCountry();
        this.addresses = city.getAddresses();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public LinkedList<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(LinkedList<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        City city = (City) o;

        return name != null ? name.equals(city.name) : city.name == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
