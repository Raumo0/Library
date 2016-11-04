package com.netcracker.library.dao.mysql;

import java.sql.Connection;

/**
 * Created by raumo0 on 04.11.16.
 */
public abstract class MysqlAbstractDAO {
    protected final String tableName;
    protected Connection connection;

    protected MysqlAbstractDAO(Connection connection, String tableName) {
        this.tableName = tableName;
        this.connection = connection;
    }
}