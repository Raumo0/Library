package com.netcracker.library.service;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.exceptions.BookException;

import java.util.ArrayList;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface BookService {
    public Book getById(long id);

    public long insertBook(Book book);

    public boolean deleteBook(long id) throws BookException;

    public boolean updateBook(Book book);

    public ArrayList<Book> getByState(BookState state);

    public ArrayList<Book> getByPosition(BookPosition position);
}
