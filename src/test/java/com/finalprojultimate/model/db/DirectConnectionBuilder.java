package com.finalprojultimate.model.db;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DirectConnectionBuilder implements ConnectionBuilder {

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/cashsysdb?allowMultiQueries=true",
                "root", "toor");
    }
}
