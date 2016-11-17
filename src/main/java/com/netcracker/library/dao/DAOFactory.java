package com.netcracker.library.dao;

import com.netcracker.library.dao.database.DAOManager;

/**
 * Created by raumo0 on 20.10.16.
 */
public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int MYSQL = 1;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract PersonDAO getPersonDAO();
    public abstract AuthorDAO getAuthorDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return DAOManager.getInstance();
            default:
                return null;
        }
    }
}
