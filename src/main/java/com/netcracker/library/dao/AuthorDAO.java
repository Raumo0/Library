package com.netcracker.library.dao;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 14.11.16.
 */
public interface AuthorDAO extends AbstractDAO<Author> {
    Author getAuthorByPersonId(int personId) throws DAOException;

    Collection<Author> getAuthorsByBookEditionId(int bookEditionId) throws DAOException;
}
