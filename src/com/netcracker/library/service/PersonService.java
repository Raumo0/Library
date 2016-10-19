package com.netcracker.library.service;

import com.netcracker.library.entities.Person;
import com.netcracker.library.enums.Country;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface PersonService {
    Person searchById(long id);

    Person searchByEmail(String email);

    ArrayList<Person> searchByFirstName(String firstName);

    ArrayList<Person> searchByLastName(String lastName);

    ArrayList<Person> searchByCountry(Country country);

    ArrayList<Person> searchByBirthday(GregorianCalendar birthday);
}
