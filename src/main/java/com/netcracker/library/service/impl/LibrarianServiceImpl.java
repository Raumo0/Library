package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Librarian;
import com.netcracker.library.entities.Person;
import com.netcracker.library.enums.Country;
import com.netcracker.library.service.LibrarianService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 21.10.16.
 */
public class LibrarianServiceImpl implements LibrarianService {
    private static ArrayList<Librarian> librarians = new ArrayList<>();


    @Override
    public Librarian getById(long id) {
        for (Librarian author : librarians) {
            if (author.getId() == id){
                return  author;
            }
        }
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
    public long insertLibrarian(Librarian librarian) {
        if (librarians.size() == 0) {
            librarian.setId(1);
        }
        else {
            librarian.setId(librarians.get(librarians.size() - 1).getId() + 1);
        }
        librarians.add(librarian);
        return librarian.getId();
    }

    @Override
    public Collection getLibrarians() {
        return librarians;
    }
}
