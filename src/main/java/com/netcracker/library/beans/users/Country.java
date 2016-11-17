package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Entity;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class Country extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<City> cities;

    public Country() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Country country = (Country) o;

        if (name != null ? !name.equals(country.name) : country.name != null) return false;
        return cities != null ? cities.equals(country.cities) : country.cities == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (cities != null ? cities.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Country{" +
                "name='" + name + '\'' +
                ", cities=" + cities +
                "} " + super.toString();
    }
}