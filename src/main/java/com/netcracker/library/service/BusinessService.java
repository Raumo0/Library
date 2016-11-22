package com.netcracker.library.service;

import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.exceptions.DAOException;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public interface BusinessService {
    int addRental(Rental rental) throws DAOException;

    Rental getRentalById(int id) throws DAOException;

    boolean updateRental(Rental rental) throws DAOException;

    boolean deleteRentalById(int id) throws DAOException;

    Collection<Rental> getRentalAll() throws DAOException;

    boolean deleteRentalAll() throws DAOException;

    Collection<Rental> getRentalsByUserId(int userId) throws DAOException;

    Collection<Rental> getRentalsByBookId(int bookId) throws DAOException;
}
