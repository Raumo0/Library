package com.netcracker.library.dao;

import com.netcracker.library.dao.binary.BinaryDAOFactory;
import com.netcracker.library.dao.text.TextDAOFactory;

/**
 * Created by raumo0 on 20.10.16.
 */
public abstract class DAOFactory {
    // List of DAO types supported by the factory
    public static final int CLOUDSCAPE = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;
    public static final int BINARY = 4;
    public static final int TEXT = 5;

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    public abstract BookDAO getBookDAO();
//    public abstract AccountDAO getAccountDAO();
//    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
//            case CLOUDSCAPE:
//                return new CloudscapeDAOFactory();
//            case ORACLE    :
//                return new OracleDAOFactory();
//            case SYBASE    :
//                return new SybaseDAOFactory();
            case BINARY:
                return new BinaryDAOFactory();
            case TEXT:
                return new TextDAOFactory();
            default           :
                return null;
        }
    }
}
