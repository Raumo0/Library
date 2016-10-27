package com.netcracker.library.service;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.exceptions.BookException;

import java.util.Collection;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface BookService {
    Book getById(long id);

    long insertBook(Book book);

    boolean deleteBook(long id) throws BookException;

    boolean updateBook(Book book);

    Collection<Book> getBooks();

    Collection<Book> getByState(BookState state);

    Collection<Book> getByPosition(BookPosition position);
}
