package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Entity;

import java.util.LinkedList;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Country extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private LinkedList<City> cities;

    public Country() {}

    public Country(Country country){
        super(country);
        this.name = country.getName();
        this.cities = country.getCities();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<City> getCities() {
        return cities;
    }

    public void setCities(LinkedList<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Country country = (Country) o;

        return name != null ? name.equals(country.name) : country.name == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
