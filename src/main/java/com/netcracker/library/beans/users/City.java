package com.netcracker.library.beans.users;

import com.netcracker.library.beans.Entity;

import java.util.List;

/**
 * Created by raumo0 on 15.11.16.
 */
public class City extends Entity {
    private static final long serialVersionUID = 1L;
    private String name;
    private Country country;
    private List<Address> addresses;

    public City() {}

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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        City city = (City) o;

        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (country != null ? !country.equals(city.country) : city.country != null) return false;
        return addresses != null ? addresses.equals(city.addresses) : city.addresses == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (addresses != null ? addresses.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", country=" + country +
                ", addresses=" + addresses +
                "} " + super.toString();
    }
}
