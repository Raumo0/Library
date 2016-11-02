package com.netcracker.library.service;

import com.netcracker.library.entities.Cartulary;

import java.util.Collection;

/**
 * Created by raumo0 on 27.10.16.
 */
public interface CartularyService {
    long insertCartulary(Cartulary cartulary);

    Cartulary getById(long id);

    Collection<Cartulary> getByReaderId(long id);

    Collection<Cartulary> getByBookId(long id);

    Collection<Cartulary> getCartularies();
}
