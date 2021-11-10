package com.finalprojultimate.model.db.dao.connection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnectionBuilder implements ConnectionBuilder {
    private static final Logger logger = LoggerFactory.getLogger(PoolConnectionBuilder.class);

    private static DataSource dataSource;

    private PoolConnectionBuilder() {
        // private constructor
    }

    public static synchronized PoolConnectionBuilder getInstance() {
        if (dataSource == null) {
            try {
                Context ctx = new InitialContext();
                dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/cashSys");
            } catch(NamingException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return new PoolConnectionBuilder();
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
