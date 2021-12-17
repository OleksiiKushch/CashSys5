package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.exception.ApplicationException;
import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.Entity;
import com.finalprojultimate.model.entity.user.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.List;

import static com.finalprojultimate.model.db.dao.util.LogMessage.DELETE_ROW_FROM_DATABASE_LOG_MSG;
import static com.finalprojultimate.model.db.dao.util.LogMessage.GET_COUNT_ROWS_IN_DATABASE_LOG_MSG;
import static com.finalprojultimate.util.MessageKey.ERROR_DELETE_ROW_FROM_DATABASE;
import static com.finalprojultimate.util.MessageKey.ERROR_GET_COUNT_ROWS_IN_DATABASE;

public interface EntityDAO<T extends Entity> {
    Logger logger = Logger.getLogger(EntityDAO.class);

    void setConnectionBuilder(ConnectionBuilder connectionBuilder);
    Connection getConnection() throws SQLException;

    int insert(T t) throws DaoException;
    int update(T t) throws DaoException;
    int delete(T t) throws DaoException;
    T getById(int id) throws DaoException;
    List<T> getAll() throws DaoException;

    default int deleteByQuery(T t, String query) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, t.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_DELETE_ROW_FROM_DATABASE,
                    DELETE_ROW_FROM_DATABASE_LOG_MSG, getClass());
        }
    }

    default int getCountByQuery(String query) throws DaoException {
        int result = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GET_COUNT_ROWS_IN_DATABASE,
                    GET_COUNT_ROWS_IN_DATABASE_LOG_MSG, getClass());
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

    default ApplicationException generateException(String message, String logMessage, Class<?> classThatThrows) {
        return new DaoException()
                .addMessage(message)
                .addLogMessage(logMessage)
                .setClassThrowsException(classThatThrows);
    }
}
