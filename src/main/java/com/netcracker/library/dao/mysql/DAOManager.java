package com.netcracker.library.dao.mysql;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import com.netcracker.library.dao.AuthorDAO;
import com.netcracker.library.dao.DAOFactory;
import com.netcracker.library.dao.PersonDAO;

import java.sql.Driver;
import java.sql.DriverManager;

/**
 * Created by raumo0 on 04.11.16.
 * So, thanks to this we can now have one instance per thread.
 * When you call close(), you close every single connection the DAOs are using
 */
public class DAOManager extends DAOFactory {
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

    @Override
    public PersonDAO getPersonDAO() {
        return new DatabasePersonDAO();
    }

    @Override
    public AuthorDAO getAuthorDAO() {
        return new DatabaseAuthorDAO();
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