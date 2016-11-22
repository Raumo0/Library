package com.netcracker.library.dao;

import com.netcracker.library.beans.users.Role;
import com.netcracker.library.exceptions.DAOException;

/**
 * Created by raumo0 on 20.11.16.
 */
public interface RoleDAO extends AbstractDAO<Role> {
    Role getRoleByUserId(int userId) throws DAOException;
}
