package com.netcracker.library.dao;

import com.netcracker.library.entities.Reader;

import java.util.Collection;

/**
 * Created by raumo0 on 31.10.16.
 */
public interface ReaderDAO {
    boolean saveAll(Collection<Reader> readers);

    Collection<Reader> readAll();
}
