package com.netcracker.library.entities;

import com.netcracker.library.enums.Country;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 14.10.16.
 */
public abstract class Person implements Serializable {
    private String firstName;
    private String lastName;
    private String email;
    private long id;
    private GregorianCalendar birthday;
    private Country country;

    public Person(String firstName, String lastName, String email, long id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
    }

    public Person(String firstName, String lastName, String email, long id, GregorianCalendar birthday,
                  Country country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.birthday = birthday;
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public GregorianCalendar getBirthday() {
        return birthday;
    }

    public void setBirthday(GregorianCalendar birthday) {
        this.birthday = birthday;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                ", birthday=" + birthday +
                ", country=" + country +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (!firstName.equals(person.firstName)) return false;
        if (!lastName.equals(person.lastName)) return false;
        if (!email.equals(person.email)) return false;
        if (birthday != null ? !birthday.equals(person.birthday) : person.birthday != null) return false;
        return country == person.country;

    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }
}
