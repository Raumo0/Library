package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Person;
import com.netcracker.library.entities.Reader;
import com.netcracker.library.enums.Country;
import com.netcracker.library.service.ReaderService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 21.10.16.
 */
public class ReaderServiceImpl implements ReaderService {
    private static ArrayList<Reader> readers = new ArrayList<>();

    @Override
    public Reader getById(long id) {
        for (Reader author : readers) {
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
    public Collection getReaders() {
        return readers;
    }

    @Override
    public long insertReader(Reader reader) {
        if (readers.size() == 0) {
            reader.setId(1);
        }
        else {
            reader.setId(readers.get(readers.size() - 1).getId() + 1);
        }
        readers.add(reader);
        return reader.getId();
    }
}
