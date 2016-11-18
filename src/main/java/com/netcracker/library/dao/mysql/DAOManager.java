package com.netcracker.library.dao.mysql;

import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.PersonDAO;

/**
 * Created by raumo0 on 04.11.16.
 * So, thanks to this we can now have one instance per thread.
 * When you call close(), you close every single connection the DAOs are using
 */
public class DAOManager extends DAOFactory {
    private DAOManager() {}

    public static DAOManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public PersonDAO getPersonDAO() {
        return new DatabasePersonDAO();
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return new DatabaseAuthorDAO();
    }

    private static class SingletonHolder {
        private static final DAOManager INSTANCE = new DAOManager();
    }
}