package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.exception.ApplicationException;
import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.db.dao.mysql.MySqlReceiptDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface EntityDAO<T> {
    Logger logger = Logger.getLogger(EntityDAO.class);

    void setConnectionBuilder(ConnectionBuilder connectionBuilder);

    Connection getConnection() throws SQLException;

    void insert(T t) throws DaoException;

    void update(T t) throws DaoException;

    void delete(T t) throws DaoException;

    T getById(int id) throws DaoException;

    List<T> getAll() throws DaoException;

    default int getCountByQuery(String query) throws DaoException {
        int result = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    default void rollback(Connection con) {
        try {
            con.rollback();
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
