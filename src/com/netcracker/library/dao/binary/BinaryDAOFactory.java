package com.netcracker.library.dao.binary;

import com.netcracker.library.dao.BookDAO;
import com.netcracker.library.dao.DAOFactory;

/**
 * Created by raumo0 on 20.10.16.
 */
public class BinaryDAOFactory extends DAOFactory {
    @Override
    public BookDAO getBookDAO() {
        return new BinaryBookDAO();
    }
}
