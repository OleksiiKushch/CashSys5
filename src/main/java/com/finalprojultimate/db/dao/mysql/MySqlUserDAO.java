package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.UserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlUserDAO implements UserDAO {

    @Override
    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
}
