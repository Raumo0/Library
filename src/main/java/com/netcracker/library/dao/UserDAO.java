package com.netcracker.library.dao;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 19.11.16.
 */
public interface UserDAO extends AbstractDAO<User> {
    User getUserByPersonId(int personId) throws DAOException;

    User getUserByRentalId(int rentalId) throws DAOException;

    User getStaffUserByRentalId(int rentalId) throws DAOException;

    Collection<User> getUsersByRoleId(int roleId) throws DAOException;
}
