package com.netcracker.library.dao.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.ReaderDAO;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by raumo0 on 04.11.16.
 * So, thanks to this we can now have one instance per thread.
 * When you call close(), you close every single connection the DAOs are using
 */
public class DAOManager extends DAOFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mysql";
    private Connection connection;

    private DAOManager() throws Exception {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (Exception e) {
            throw e;
        }
    }

    public static DAOManager getInstance() {
        return DAOManagerSingleton.INSTANCE.get();
    }

    public void open() throws SQLException {
        try{
            if(this.connection ==null || this.connection.isClosed())
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        }
        catch(SQLException e) {
            throw e;
        }
    }

    public void close() throws SQLException {
        try{
            if(this.connection !=null && !this.connection.isClosed())
                this.connection.close();
        } catch(SQLException e) {
            throw e;
        }
    }

    @Override
    public ReaderDAO getReaderDAO() throws SQLException {
        try {
            open();
        } catch (SQLException e) {
            throw e;
        }
        return new DatabaseReaderDAO(this.connection);
    }

    @Override
    protected void finalize() throws Throwable {
        try{
            this.close();
        } finally{
            super.finalize();
        }
    }

    private static class DAOManagerSingleton {
        private static final ThreadLocal<DAOManager> INSTANCE;
        static{
            ThreadLocal<DAOManager> dm;
            try {
                dm = new ThreadLocal<DAOManager>(){
                    @Override
                    protected DAOManager initialValue() {
                        try {
                            return new DAOManager();
                        }
                        catch(Exception e){
                            return null;
                        }
                    }
                };
            }
            catch(Exception e) {
                dm = null;
            }
            INSTANCE = dm;
        }
    }
}