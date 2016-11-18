package com.netcracker.library.service.impl;

import com.netcracker.library.beans.books.Author;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.AuthorService;

import java.util.Collection;

/**
 * Created by raumo0 on 18.11.16.
 */
public class AuthorServiceImpl implements AuthorService {
    private DAOFactory factory;
    private AuthorDAO authorDAO;

    private AuthorServiceImpl() {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        authorDAO = factory.getAuthorDAO();
    }

    public static AuthorServiceImpl getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int insert(Author author) throws DAOException {
        return authorDAO.insert(author);
    }

    @Override
    public Author getById(int id) throws DAOException {
        return authorDAO.getById(id);
    }

    @Override
    public boolean update(Author author) throws DAOException {
        throw new DAOException();
    }

    @Override
    public boolean deleteById(int id) throws DAOException {
        throw new DAOException();
    }

    @Override
    public Collection<Author> getAll() throws DAOException {
        throw new DAOException();
    }

    private static class SingletonHolder {
        private static final AuthorServiceImpl INSTANCE = new AuthorServiceImpl();
    }
}
