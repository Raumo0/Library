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
    BookEdition searchById(long id);

    BookEdition searchByISBN(String isbn);

    ArrayList<BookEdition> searchByTitle(String title);

    ArrayList<BookEdition> searchByReleaseDate(GregorianCalendar releaseDate);

    ArrayList<BookEdition> searchByCategories(List<BookCategory> categories);

    ArrayList<BookEdition> searchByLanguage(BookLanguage language);

    ArrayList<BookEdition> searchByPageCount(int pageCount);
}
