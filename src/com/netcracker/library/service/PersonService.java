package com.netcracker.library.service;

import com.netcracker.library.entities.Person;
import com.netcracker.library.enums.Country;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface PersonService {
    Person getById(long id);

    Person getByEmail(String email);

    ArrayList<Person> getByFirstName(String firstName);

    ArrayList<Person> getByLastName(String lastName);

    ArrayList<Person> getByCountry(Country country);

    ArrayList<Person> getByBirthday(GregorianCalendar birthday);
}
