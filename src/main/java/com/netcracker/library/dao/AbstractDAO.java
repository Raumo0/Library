package com.netcracker.library.dao;

import com.netcracker.library.beans.Entity;

import java.util.Collection;

/**
 * Created by raumo0 on 17.11.16.
 */
public interface AbstractDAO <T extends Entity> {
    int insert(T reader);

    T getById(int id);

    boolean update(T reader);

    boolean deleteById(int id);

    Collection<T> getAll();
}
