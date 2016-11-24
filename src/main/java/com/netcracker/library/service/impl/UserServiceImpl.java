package com.netcracker.library.service.impl;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.UserDAO;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.service.UserService;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public class UserServiceImpl implements UserService {
    private DAOFactory factory;
    private UserDAO userDAO;

    private UserServiceImpl(){
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        userDAO = factory.getUserDAO();
    }

    public static UserServiceImpl getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int addUser(User user) throws DAOException {
        return userDAO.insert(user);
    }

    @Override
    public User getUserById(int id) throws DAOException {
        return userDAO.getById(id);
    }

    @Override
    public boolean updateUser(User user) throws DAOException {
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUserById(int id) throws DAOException {
        return userDAO.deleteById(id);
    }

    @Override
    public Collection<User> getUserAll() throws DAOException {
        return userDAO.getAll();
    }

    @Override
    public boolean deleteUserAll() throws DAOException {
        return userDAO.deleteAll();
    }

    @Override
    public User getUserByPersonId(int personId) throws DAOException {
        return userDAO.getUserByPersonId(personId);
    }

    @Override
    public User getUserByRentalId(int rentalId) throws DAOException {
        return userDAO.getUserByRentalId(rentalId);
    }

    @Override
    public User getStaffUserByRentalId(int rentalId) throws DAOException {
        return userDAO.getStaffUserByRentalId(rentalId);
    }

    @Override
    public Collection<User> getUsersByRole(UserRole role) throws DAOException {
        return userDAO.getUsersByRole(role);
    }

    private static class SingletonHolder{
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }
}
