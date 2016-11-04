package com.netcracker.library.dao;

import com.netcracker.library.entities.Reader;

import java.util.Collection;

/**
 * Created by raumo0 on 31.10.16.
 */
public interface ReaderDAO {
    int insert(Reader reader);

    Reader read(int id);

    boolean update(Reader reader);

    boolean delete(int id);

    boolean saveAll(Collection<Reader> readers);

    Collection<Reader> readAll();
}
