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

    boolean update(T reader);

    boolean deleteById(int id);

    Collection<T> getAll();
}
