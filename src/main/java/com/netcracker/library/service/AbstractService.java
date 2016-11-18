package com.netcracker.library.service;

import com.netcracker.library.beans.Entity;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 18.11.16.
 */
public interface AbstractService <T extends Entity> {
    int insert(T entity) throws DAOException;

    T getById(int id) throws DAOException;

    boolean update(T entity) throws DAOException;

    boolean deleteById(int id) throws DAOException;

    Collection<T> getAll() throws DAOException;
}
