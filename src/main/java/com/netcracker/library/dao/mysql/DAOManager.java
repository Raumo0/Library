package com.netcracker.library.dao.mysql;

import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.BookEditionDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.UserDAO;

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
    public AuthorDAO getAuthorDAO() {
        return new MysqlAuthorDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MysqlUserDAO();
    }

    @Override
    public BookEditionDAO getBookEditionDAO() {
        return new MysqlBookEditionDAO();
    }

    private static class SingletonHolder {
        private static final DAOManager INSTANCE = new DAOManager();
    }
}