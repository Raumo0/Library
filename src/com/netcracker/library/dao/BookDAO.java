package com.netcracker.library.dao;

import com.netcracker.library.entities.Book;
import com.netcracker.library.enums.BookPosition;
import com.netcracker.library.enums.BookState;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by raumo0 on 20.10.16.
 */
public interface BookDAO {
    // Implement insertBookEdition book here.
    // Return newly created book number
    // or a -1 on error
    public long insertBook(Book book);

    // Implement delete book here
    // Return true on success, false on failure
    public boolean deleteBook(long id);

    // Implement find a book here using supplied
    // argument values as search criteria
    // Return a Transfer Object if found,
    // return null on error or if not found
    public Book findBook(long id);

    // implement update record here using data
    // from the bookData Transfer Object
    // Return true on success, false on failure or
    // error
    public boolean updateBook(Book book);

    // implement search books here using the
    // supplied criteria.
    // Alternatively, implement to return a Collection
    // of Transfer Objects.
    public Collection selectBooksByState(BookState state);

    // implement search books here using the
    // supplied criteria.
    // Alternatively, implement to return a Collection
    // of Transfer Objects.
    public Collection selectBooksByPosition(BookPosition position);
}
