package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.exception.ApplicationException;
import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface EntityDAO<T> {
    void setConnectionBuilder(ConnectionBuilder connectionBuilder);

    Connection getConnection() throws SQLException;

    void insert(T t) throws DaoException;

    void update(T t) throws DaoException;

    void delete(T t) throws DaoException;

    T getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    default void rollback(Connection con) {
        try {
            con.rollback();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    default void setAutoCommit(Connection con) {
        try {
            con.setAutoCommit(true);
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    default void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    default void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    default ApplicationException generateException(String message, String logMessage, Class<?> classThatThrows) {
        return new DaoException()
                .addMessage(message)
                .addLogMessage(logMessage)
                .setClassThrowsException(classThatThrows);
    }
}
