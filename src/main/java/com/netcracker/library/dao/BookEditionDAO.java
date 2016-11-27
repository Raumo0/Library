package com.netcracker.library.dao;

import com.netcracker.library.beans.books.BookEdition;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by raumo0 on 20.11.16.
 */
public interface BookEditionDAO extends AbstractDAO<BookEdition> {
    LinkedList<BookEdition> getBookEditionsByAuthorId(int authorId) throws DAOException;

    BookEdition getBookEditionByBookId(int bookId) throws DAOException;

    Collection<BookEdition> getBookEditionsByGap(int offset, int quantity) throws DAOException;
}
