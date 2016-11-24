package com.netcracker.library.service;

import com.netcracker.library.beans.users.Role;
import com.netcracker.library.beans.users.User;
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

    Collection<User> getUsersByRoleId(int roleId) throws DAOException;

    int addRole(Role role) throws DAOException;

    Role getRoleById(int id) throws DAOException;

    boolean updateRole(Role role) throws DAOException;

    boolean deleteRoleById(int id) throws DAOException;

    Collection<Role> getRoleAll() throws DAOException;

    boolean deleteRoleAll() throws DAOException;

    Role getRoleByUserId(int userId) throws DAOException;
}
