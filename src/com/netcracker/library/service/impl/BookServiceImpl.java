package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.exceptions.BookException;
import com.netcracker.library.service.BookService;

import java.util.ArrayList;

/**
 * Created by raumo0 on 21.10.16.
 */
public class BookServiceImpl implements BookService {
    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public long insertBook(Book book) {
        return 0;
    }

    @Override
    public boolean deleteBook(long id) throws BookException {
        return false;
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public ArrayList<Book> getByState(BookState state) {
        return null;
    }

    @Override
    public ArrayList<Book> getByPosition(BookPosition position) {
        return null;
    }
}
