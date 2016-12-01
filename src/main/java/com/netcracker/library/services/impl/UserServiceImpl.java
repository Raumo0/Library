package com.netcracker.library.services.impl;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.UserDAO;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.services.UserService;

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
    public int addUser(User user) throws ServiceException {
        try {
            return userDAO.insert(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserById(int id) throws ServiceException {
        try {
            return userDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws ServiceException {
        try {
            return userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUserById(int id) throws ServiceException {
        try {
            return userDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<User> getUserAll() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteUserAll() throws ServiceException {
        try {
            return userDAO.deleteAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByPersonId(int personId) throws ServiceException {
        try {
            return userDAO.getUserByPersonId(personId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUserByRentalId(int rentalId) throws ServiceException {
        try {
            return userDAO.getUserByRentalId(rentalId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getStaffUserByRentalId(int rentalId) throws ServiceException {
        try {
            return userDAO.getStaffUserByRentalId(rentalId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<User> getUsersByRole(UserRole role) throws ServiceException {
        try {
            return userDAO.getUsersByRole(role);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User isAuthorized(String username, String password, String salt) throws ServiceException {
        try {
            return userDAO.isAuthorized(username, password, salt);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private static class SingletonHolder{
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }
}
