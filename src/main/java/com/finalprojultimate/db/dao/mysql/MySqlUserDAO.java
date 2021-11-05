package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.UserDAO;
import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.db.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDAO implements UserDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySqlUserDAO.class);

    private ConnectionBuilder connectionBuilder;

    @Override
    public void setConnectionBuilder(ConnectionBuilder connectionBuilder) {
        this.connectionBuilder = connectionBuilder;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionBuilder.getConnection();
    }

    @Override
    public void save(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.CREATE_USER)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getMiddleName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassHash());
            ps.setInt(5, user.getRole().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
    }

    /**
     *
     * @param uBefore - the user to be changed
     * @param uAfter - the user to be exchanged for the old one
     * update product from DB, search by id user
     */
    @Override
    public void update(User uBefore, User uAfter) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.UPDATE_USER)) {
            ps.setString(1, uAfter.getFirstName());
            ps.setString(2, uAfter.getMiddleName());
            ps.setString(3, uAfter.getLastName());
            ps.setString(4, uAfter.getPassHash());
            ps.setInt(5, uAfter.getRole().getId());
            ps.setInt(6, uBefore.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
    }

    /**
     *
     * @param user
     * remove user from DB, search by id user
     */
    @Override
    public void delete(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.DELETE_USER_BY_ID)) {
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
    }

    @Override
    public User getById(int id) throws DaoException {
        User result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.GET_USER_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
        return result;
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.GET_ALL_USERS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
        return result;
    }

    private User mapUser(ResultSet rs) throws SQLException {
        return new User.Builder()
                .withId(rs.getInt(MySqlConstants.UserField.ID))
                .withFirstName(rs.getString(MySqlConstants.UserField.FIRST_NAME))
                .withMiddleName(rs.getString(MySqlConstants.UserField.MIDDLE_NAME))
                .withLastName(rs.getString(MySqlConstants.UserField.LAST_NAME))
                .withPassHash(rs.getString(MySqlConstants.UserField.PASS_HASH))
                .withRoleId(User.Role.getById(rs.getInt(MySqlConstants.UserField.ROLE_ID)))
                .build();
    }
}
