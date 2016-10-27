package com.netcracker.library.service.impl;

import com.netcracker.library.entities.Cartulary;
import com.netcracker.library.service.CartularyService;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by raumo0 on 27.10.16.
 */
public class CartularyServiceImpl implements CartularyService {
    private LinkedList<Cartulary> cartularies;

    public CartularyServiceImpl(){
        cartularies = new LinkedList<>();
    }

    @Override
    public long insertCartulary(Cartulary cartulary) {
        if (cartularies.size() == 0) {
            cartulary.setId(1);
        }
        else {
            cartulary.setId(cartularies.get(cartularies.size() - 1).getId() + 1);
        }
        cartularies.add(cartulary);
        return cartulary.getId();
    }

    @Override
    public Cartulary getById(long id) {
        for (Cartulary author : cartularies) {
            if (author.getId() == id){
                return  author;
            }
        }
        return null;
    }

    @Override
    public Collection<Cartulary> getByReaderId(long id) {
        Collection<Cartulary> cartulariesByReaderID = new LinkedList<>();
        for (Cartulary cartulary : cartularies) {
            if (cartulary.getReader().getId() == id){
                cartulariesByReaderID.add(cartulary);
            }
        }
        return cartulariesByReaderID;
    }

    @Override
    public Collection<Cartulary> getByBookId(long id) {
        Collection<Cartulary> cartulariesByBookID = new LinkedList<>();
        for (Cartulary cartulary : cartularies) {
            if (cartulary.getBook().getId() == id){
                cartulariesByBookID.add(cartulary);
            }
        }
        return cartulariesByBookID;
    }

    @Override
    public Collection<Cartulary> getCartularies() {
        return cartularies;
    }
}
