package com.netcracker.library.dao.mysql;

import org.apache.logging.log4j.core.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//import org.apache.log4j.Logger;

/**
 * Created by raumo0 on 17.11.16.
 */
public class  ConnectionPool {
    private DataSource dataSource;
    private Connection connection;
    private Logger logger;
    {
        try{
            InitialContext initContext = new InitialContext();
            dataSource = (DataSource) initContext.lookup("java:/comp/env/jdbc/library");
        }
        catch(NamingException e){
            e.printStackTrace();
//            logger.error("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n\n\n\n\n\n" +
//                    e.getMessage());
//            PaymentSystemLogger.INSTANCE.logError(getClass(), e.getMessage());
        }
    }

    private ConnectionPool() {}

    public static ConnectionPool getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        connection = dataSource.getConnection();
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if(connection != null){
            try {
                connection.close();
            }
            catch (SQLException e) {
//                PaymentSystemLogger.INSTANCE.logError(getClass(), e.getMessage());
            }
        }
    }

    private static class SingletonHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
