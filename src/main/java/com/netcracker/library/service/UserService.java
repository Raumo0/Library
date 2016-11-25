package com.netcracker.library.service;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.enums.UserRole;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public interface UserService {
    int addUser(User user) throws DAOException;

    User getUserById(int id) throws DAOException;

    boolean updateUser(User user) throws DAOException;

    boolean deleteUserById(int id) throws DAOException;

    Collection<User> getUserAll() throws DAOException;

    boolean deleteUserAll() throws DAOException;

    User getUserByPersonId(int personId) throws DAOException;

    User getUserByRentalId(int rentalId) throws DAOException;

    User getStaffUserByRentalId(int rentalId) throws DAOException;

    Collection<User> getUsersByRole(UserRole role) throws DAOException;

    User isAuthorized(String username, String password, String salt) throws DAOException;
}
