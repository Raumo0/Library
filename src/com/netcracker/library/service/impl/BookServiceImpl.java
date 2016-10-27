package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;
import com.netcracker.library.exceptions.BookException;
import com.netcracker.library.service.BookService;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by raumo0 on 21.10.16.
 */
public class BookServiceImpl implements BookService {
    private static ArrayList<Book> books = new ArrayList<>();

    @Override
    public Book getById(long id) {
        for (Book book : books) {
            if (book.getId() == id)
                return book;
        }
        return null;
    }

    @Override
    public long insertBook(Book book) {
        if (books.size() == 0) {
            book.setId(1);
        }
        else {
            book.setId(books.get(books.size() - 1).getId() + 1);
        }
        books.add(book);
        return book.getId();
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
    public Collection<Book> getBooks() {
        return books;
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
