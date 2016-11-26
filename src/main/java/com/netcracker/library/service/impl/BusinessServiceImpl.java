package com.netcracker.library.service.impl;

import com.netcracker.library.beans.business.Rental;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.RentalDAO;
import com.netcracker.library.exceptions.DAOException;
import com.netcracker.library.exceptions.ServiceException;
import com.netcracker.library.service.BusinessService;

import java.util.Collection;

/**
 * Created by raumo0 on 22.11.16.
 */
public class BusinessServiceImpl implements BusinessService {
    private DAOFactory factory;
    private RentalDAO rentalDAO;

    private BusinessServiceImpl() {
        factory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        rentalDAO = factory.getRentalDAO();
    }

    public static BusinessServiceImpl getInstance(){
        return SingletonHolder.INSTANCE;
    }

    @Override
    public int addRental(Rental rental) throws ServiceException {
        try {
            return rentalDAO.insert(rental);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Rental getRentalById(int id) throws ServiceException {
        try {
            return rentalDAO.getById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean updateRental(Rental rental) throws ServiceException {
        try {
            return rentalDAO.update(rental);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteRentalById(int id) throws ServiceException {
        try {
            return rentalDAO.deleteById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Rental> getRentalAll() throws ServiceException {
        try {
            return rentalDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteRentalAll() throws ServiceException {
        try {
            return rentalDAO.deleteAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Rental> getRentalsByUserId(int userId) throws ServiceException {
        try {
            return rentalDAO.getRentalsByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Collection<Rental> getRentalsByBookId(int bookId) throws ServiceException {
        try {
            return rentalDAO.getRentalsByBookId(bookId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private static class SingletonHolder{
        private static final BusinessServiceImpl INSTANCE = new BusinessServiceImpl();
    }
}
