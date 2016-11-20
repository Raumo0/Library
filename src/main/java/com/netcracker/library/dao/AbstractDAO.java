package com.netcracker.library.dao;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 17.11.16.
 */
public interface AbstractDAO <T extends Entity> {
    int insert(T reader) throws DAOException;

    T getById(int id) throws DAOException;

    boolean update(T reader) throws DAOException;

    boolean deleteById(int id) throws DAOException;

    Collection<T> getAll() throws DAOException;

    boolean deleteAll() throws DAOException;
}
