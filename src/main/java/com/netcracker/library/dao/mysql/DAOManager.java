package com.netcracker.library.dao.mysql;

import com.netcracker.library.dao.*;

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

    @Override
    public RoleDAO getRoleDAO() {
        return new MysqlRoleDAO();
    }

    @Override
    public RentalDAO getRentalDAO() {
        return new MysqlRentalDAO();
    }

    @Override
    public BookDAO getBookDAO() {
        return new MysqlBookDAO();
    }

    private static class SingletonHolder {
        private static final DAOManager INSTANCE = new DAOManager();
    }
}