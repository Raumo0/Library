package com.netcracker.library.service;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.util.ArrayList;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface BookService {
    Book getById(long id);

    ArrayList<Book> getByState(BookState state);

    ArrayList<Book> getByPosition(BookPosition position);
}
