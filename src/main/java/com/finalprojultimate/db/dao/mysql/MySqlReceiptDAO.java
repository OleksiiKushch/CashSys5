package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ReceiptDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlReceiptDAO implements ReceiptDAO {

    @Override
    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {

    }

    @Override
    public Connection getConnection() throws SQLException {
        return null;
    }
}
