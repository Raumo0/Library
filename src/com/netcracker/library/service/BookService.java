package com.netcracker.library.service;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.util.ArrayList;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface BookService {
    Book searchById(long id);

    ArrayList<Book> searchByState(BookState state);

    ArrayList<Book> searchByPosition(BookPosition position);
}
