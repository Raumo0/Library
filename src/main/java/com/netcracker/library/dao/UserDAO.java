package com.netcracker.library.dao;

import com.netcracker.library.beans.users.User;
import com.netcracker.library.exceptions.DAOException;

/**
 * Created by raumo0 on 19.11.16.
 */
public interface UserDAO extends AbstractDAO<User> {
    User getUserByPersonId(int personId) throws DAOException;
}
