package com.netcracker.library.service;

import com.netcracker.library.entities.BookEdition;
import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.BookLanguage;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface BookEditionService {
    BookEdition getById(long id);

    BookEdition getByISBN(String isbn);

    ArrayList<BookEdition> getByTitle(String title);

    ArrayList<BookEdition> getByReleaseDate(GregorianCalendar releaseDate);

    ArrayList<BookEdition> getByCategories(List<BookCategory> categories);

    ArrayList<BookEdition> getByLanguage(BookLanguage language);

    ArrayList<BookEdition> getByPageCount(int pageCount);
}
