package com.netcracker.library.service;

import com.netcracker.library.entities.BookEdition;
import com.netcracker.library.enums.BookCategory;
import com.netcracker.library.enums.BookLanguage;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface BookEditionService {
    long insertBookEdition(BookEdition bookEdition);

    Collection<BookEdition> getBookEditions();

    BookEdition getById(long id);

    BookEdition getByISBN(String isbn);

    Collection<BookEdition> getByTitle(String title);

    Collection<BookEdition> getByReleaseDate(GregorianCalendar releaseDate);

    Collection<BookEdition> getByCategories(List<BookCategory> categories);

    Collection<BookEdition> getByLanguage(BookLanguage language);

    Collection<BookEdition> getByPageCount(int pageCount);
}
