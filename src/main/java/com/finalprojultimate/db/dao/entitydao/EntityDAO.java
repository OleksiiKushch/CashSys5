package com.finalprojultimate.db.dao.entitydao;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EntityDAO<T> {
    void setConnectionBuilder(ConnectionBuilder connectionBuilder);
    Connection getConnection() throws SQLException;
    void create(T t);
    void delete(T t);
    List<T> getAll();
}
