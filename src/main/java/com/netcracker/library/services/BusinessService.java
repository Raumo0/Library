package com.netcracker.library.services;

import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.exceptions.ServiceException;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public interface BusinessService {
    int addRental(Rental rental) throws ServiceException;

    Rental getRentalById(int id) throws ServiceException;

    boolean updateRental(Rental rental) throws ServiceException;

    boolean deleteRentalById(int id) throws ServiceException;

    Collection<Rental> getRentalAll() throws ServiceException;

    boolean deleteRentalAll() throws ServiceException;

    Collection<Rental> getRentalsByUserId(int userId) throws ServiceException;

    Collection<Rental> getRentalsByBookId(int bookId) throws ServiceException;
}
