package com.finalprojultimate.db.dao.entitydao;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.db.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface EntityDAO<T> {
    void setConnectionBuilder(ConnectionBuilder connectionBuilder);
    Connection getConnection() throws SQLException;
    void save(T t) throws DaoException;
    void update(T tBefore, T tAfter) throws DaoException;
    void delete(T t) throws DaoException;
    T getById(int id) throws DaoException;
    List<T> getAll() throws DaoException;
}
