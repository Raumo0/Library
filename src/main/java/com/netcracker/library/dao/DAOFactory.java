package com.netcracker.library.dao;

//import com.netcracker.library.dao.binary.BinaryDAOFactory;
import com.netcracker.library.dao.mysql.DAOManager;
//import com.netcracker.library.dao.text.TextDAOFactory;

import java.sql.SQLException;

/**
 * Created by raumo0 on 20.10.16.
 */
public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int BINARY = 1;
    public static final int TEXT = 2;
    public static final int MYSQL = 3;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract ReaderDAO getReaderDAO() throws SQLException;

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
//            case BINARY:
//                return new BinaryDAOFactory();
//            case TEXT:
//                return new TextDAOFactory();
            case MYSQL:
                return DAOManager.getInstance();
            default:
                return null;
        }
    }
}
