package com.netcracker.library.services;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.ServiceException;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public interface UserService {
    int addUser(User user) throws ServiceException;

    User getUserById(int id) throws ServiceException;

    boolean updateUser(User user) throws ServiceException;

    boolean deleteUserById(int id) throws ServiceException;

    Collection<User> getUserAll() throws ServiceException;

    boolean deleteUserAll() throws ServiceException;

    User getUserByPersonId(int personId) throws ServiceException;

    User getUserByRentalId(int rentalId) throws ServiceException;

    User getStaffUserByRentalId(int rentalId) throws ServiceException;

    Collection<User> getUsersByRole(UserRole role) throws ServiceException;

    User isAuthorized(String username, String password, String salt) throws ServiceException;
}
