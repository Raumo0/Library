package com.netcracker.library.service;

import com.netcracker.library.entities.Person;
import com.netcracker.library.enums.Country;

import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface PersonService {
    Person getById(long id);

    Person getByEmail(String email);

    Collection<Person> getByFirstName(String firstName);

    Collection<Person> getByLastName(String lastName);

    Collection<Person> getByCountry(Country country);

    Collection<Person> getByBirthday(GregorianCalendar birthday);
}
