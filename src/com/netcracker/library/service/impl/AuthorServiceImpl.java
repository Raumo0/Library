package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Author;
import com.netcracker.library.entities.Person;
import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.Country;
import com.netcracker.library.service.AuthorService;

import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 21.10.16.
 */
public class AuthorServiceImpl implements AuthorService {
    @Override
    public Person getById(long id) {
        return null;
    }

    @Override
    public Person getByEmail(String email) {
        return null;
    }

    @Override
    public ArrayList<Person> getByFirstName(String firstName) {
        return null;
    }

    @Override
    public ArrayList<Person> getByLastName(String lastName) {
        return null;
    }

    @Override
    public ArrayList<Person> getByCountry(Country country) {
        return null;
    }

    @Override
    public ArrayList<Person> getByBirthday(GregorianCalendar birthday) {
        return null;
    }

    @Override
    public ArrayList<Author> getByBookCategory(BookCategory bookCategory) {
        return null;
    }
}
