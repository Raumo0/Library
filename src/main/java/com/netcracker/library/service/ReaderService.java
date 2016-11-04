package com.netcracker.library.service;

import com.netcracker.library.entities.Reader;

import java.util.Collection;

/**
 * Created by raumo0 on 19.10.16.
 */
public interface ReaderService extends PersonService {
    Collection<Reader> getReaders();

    void setReaders(Collection<Reader> readers);

    long insertReader(Reader reader);
}
