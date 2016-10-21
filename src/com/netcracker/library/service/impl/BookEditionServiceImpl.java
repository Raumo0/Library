package com.netcracker.library.service.impl;

import com.netcracker.library.entities.BookEdition;
import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.BookLanguage;
import com.netcracker.library.service.BookEditionService;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by raumo0 on 21.10.16.
 */
public class BookEditionServiceImpl implements BookEditionService {
    @Override
    public BookEdition getById(long id) {
        return null;
    }

    @Override
    public BookEdition getByISBN(String isbn) {
        return null;
    }

    @Override
    public ArrayList<BookEdition> getByTitle(String title) {
        return null;
    }

    @Override
    public ArrayList<BookEdition> getByReleaseDate(GregorianCalendar releaseDate) {
        return null;
    }

    @Override
    public ArrayList<BookEdition> getByCategories(List<BookCategory> categories) {
        return null;
    }

    @Override
    public ArrayList<BookEdition> getByLanguage(BookLanguage language) {
        return null;
    }

    @Override
    public ArrayList<BookEdition> getByPageCount(int pageCount) {
        return null;
    }
}
