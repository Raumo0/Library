package com.netcracker.library.dao.mysql;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Created by raumo0 on 17.11.16.
 */
public class  ConnectionPool {
    private DataSource dataSource;
    private Connection connection;

    private ConnectionPool() {
        try{
            Context initContext = new InitialContext();
            Context webContext = (Context)initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc/library");
        }
        catch(NamingException e){
            e.printStackTrace();
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }
    }

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
                java.util.logging.Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private static class SingletonHolder {
        private static final ConnectionPool INSTANCE = new ConnectionPool();
    }
}
