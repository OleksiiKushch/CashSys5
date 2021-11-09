package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
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
    public void insert(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.CREATE_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            mapUser(ps, user);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    /**
     *
     * @param user
     * update product from DB, search by id user
     */
    @Override
    public void update(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.UPDATE_USER)) {
            mapUser(ps, user);
            ps.setInt(7, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
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
            throw generateException("", "", getClass()); // Good explanation of error
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
            throw generateException("", "", getClass()); // Good explanation of error
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
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        User result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.UserQuery.GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    private void mapUser(PreparedStatement ps, User user) throws SQLException {
        int i = 0;
        ps.setString(++i, user.getEmail());
        ps.setString(++i, user.getFirstName());
        ps.setString(++i, user.getMiddleName());
        ps.setString(++i, user.getLastName());
        ps.setString(++i, user.getPassHash());
        ps.setInt(++i, user.getRole().getId());
    }

    private User mapUser(ResultSet rs) throws SQLException {
        return new User.Builder()
                .withId(rs.getInt(MySqlConstants.UserField.ID))
                .withEmail(rs.getString(MySqlConstants.UserField.EMAIL))
                .withFirstName(rs.getString(MySqlConstants.UserField.FIRST_NAME))
                .withMiddleName(rs.getString(MySqlConstants.UserField.MIDDLE_NAME))
                .withLastName(rs.getString(MySqlConstants.UserField.LAST_NAME))
                .withPassHash(rs.getString(MySqlConstants.UserField.PASS_HASH))
                .withRoleId(User.Role.getById(rs.getInt(MySqlConstants.UserField.ROLE_ID)))
                .build();
    }
}
