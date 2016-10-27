package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Author;
import com.netcracker.library.entities.Person;
import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.Country;
import com.netcracker.library.service.AuthorService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.GregorianCalendar;

/**
 * Created by raumo0 on 21.10.16.
 */
public class AuthorServiceImpl implements AuthorService {
    private static ArrayList<Author> authors = new ArrayList<>();

    @Override
    public Author getById(long id) {
        for (Author author : authors) {
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
    public long insertAuthor(Author author) {
        if (authors.size() == 0) {
            author.setId(1);
        }
        else {
            author.setId(authors.get(authors.size() - 1).getId() + 1);
        }
        authors.add(author);
        return author.getId();
    }

    @Override
    public Collection<Author> getAuthors() {
        return authors;
    }

    @Override
    public ArrayList<Author> getByBookCategory(BookCategory bookCategory) {
        return null;
    }
}
