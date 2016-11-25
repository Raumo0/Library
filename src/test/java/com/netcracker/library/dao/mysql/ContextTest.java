package com.netcracker.library.dao.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.netcracker.library.tools.SystemLogger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by raumo0 on 20.11.16.
 */
public class ContextTest {
    private ContextTest() {
        // rcarver - setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
            Context ic = new InitialContext();

            ic.createSubcontext("java:");
            ic.createSubcontext("java:/comp");
            ic.createSubcontext("java:/comp/env");
            ic.createSubcontext("java:/comp/env/jdbc");

            // Construct DataSource
            MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
            ds.setURL("jdbc:mysql://localhost:3306/library?useEncoding=yes&amp;characterEncoding=UTF-8");
            ds.setUser("root");
            ds.setPassword("mysql");
            ic.bind("java:/comp/env/jdbc/library", ds);
        } catch (NamingException e) {
            SystemLogger.getInstance().logError(getClass(), e.getMessage());
        }
    }

    public static ContextTest initializeContext() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ContextTest INSTANCE = new ContextTest();
    }
}
