package com.netcracker.library.dao;

import com.netcracker.library.beans.books.Book;
import com.netcracker.library.exceptions.DAOException;

import java.util.LinkedList;

/**
 * Created by raumo0 on 20.11.16.
 */
public interface BookDAO extends AbstractDAO<Book> {
    Book getBookByRentalId(int rentalId) throws DAOException;

    LinkedList<Book> getBooksByBookEditionId(int bookEditionId) throws DAOException;
}
