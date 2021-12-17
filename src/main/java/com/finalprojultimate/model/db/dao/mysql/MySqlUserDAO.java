package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.ReceiptQuery.DELETE_RECEIPT_BY_ID;
import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.UserQuery.*;
import static com.finalprojultimate.model.db.dao.util.LogMessage.*;
import static com.finalprojultimate.util.MessageKey.*;

public class MySqlUserDAO implements UserDAO {

    private static final Logger logger = Logger.getLogger(MySqlUserDAO.class);

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
    public int insert(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(CREATE_USER,
                     Statement.RETURN_GENERATED_KEYS)) {
            mapUser(ps, user);
            int result = ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    user.setId(rs.getInt(1));
                }
            }
            return result;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_INSERT_ROW_TO_DATABASE,
                    INSERT_ROW_TO_DATABASE_LOG_MSG, getClass());
        }
    }

    /**
     *
     * @param user
     * update product from DB, search by id user
     */
    @Override
    public int update(User user) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_USER)) {
            mapUser(ps, user);
            ps.setInt(7, user.getId());
            return ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_UPDATE_ROW_TO_DATABASE,
                    UPDATE_ROW_TO_DATABASE_LOG_MSG, getClass());
        }
    }

    /**
     *
     * @param user
     * remove user from DB, search by id user
     */
    @Override
    public int delete(User user) throws DaoException {
        return deleteByQuery(user, DELETE_USER_BY_ID);
    }

    @Override
    public User getById(int id) throws DaoException {
        User result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(GET_USER_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GETTING_ROW_FROM_DATABASE,
                    GETTING_ROW_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public List<User> getAll() throws DaoException {
        List<User> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_USERS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapUser(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GETTING_ALL_ROWS_FROM_DATABASE,
                    GETTING_ALL_ROWS_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public User getUserByEmail(String email) throws DaoException {
        User result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(GET_USER_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapUser(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GET_USER_BY_EMAIL_FROM_DATABASE,
                    GET_USER_BY_EMAIL_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    /**
     * find a specific number of users (cashiers), sorting them by the number of receipts <b>per month</b>
     *
     * @param limit limit (count) of cashiers found
     * @return LinkedHashMap where key - id user (cashiers), and
     *                             value - count of receipt
     * @throws DaoException specific exception
     */
    @Override
    public LinkedHashMap<Integer, Integer> findBestCashiersByCountReceiptForTheLastMonth(int limit)
            throws DaoException {
        LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();
        try (Connection con = getConnection(); PreparedStatement ps
                = con.prepareStatement(FIND_BEST_CASHIERS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer cashierId = rs.getInt(MySqlConstant.UserField.ID);
                    Integer count = rs.getInt(MySqlConstant.UserField.COUNT);
                    result.put(cashierId, count);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_FIND_BEST_CASHIERS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH_FROM_DATABASE,
                    FIND_BEST_CASHIERS_BY_COUNT_RECEIPT_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public List<User> findUsersByIds(Set<Integer> ids) {
        List<User> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(getById(id));
        }
        return result;
    }

    @Override
    public List<User> findUsersWithPaginationSortByNone(int offset, int limit) throws DaoException {
        return findUsersWithPaginationSortByQuery(FIND_USERS_SORT_BY_NONE, offset, limit);
    }

    @Override
    public List<User> findUsersWithPaginationSortByEmail(int offset, int limit) throws DaoException {
        return findUsersWithPaginationSortByQuery(FIND_USERS_SORT_BY_EMAIL, offset, limit);
    }

    private List<User> findUsersWithPaginationSortByQuery(String query, int offset, int limit) throws DaoException {
        List<User> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(mapUser(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_FIND_USERS_WITH_PAGINATION_FROM_DATABASE,
                    FIND_USERS_WITH_PAGINATION_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public int getCountOfUsers() throws DaoException {
        return getCountByQuery(GET_COUNT_OF_USERS);
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
                .withId(rs.getInt(MySqlConstant.UserField.ID))
                .withEmail(rs.getString(MySqlConstant.UserField.EMAIL))
                .withFirstName(rs.getString(MySqlConstant.UserField.FIRST_NAME))
                .withMiddleName(rs.getString(MySqlConstant.UserField.MIDDLE_NAME))
                .withLastName(rs.getString(MySqlConstant.UserField.LAST_NAME))
                .withPassHash(rs.getString(MySqlConstant.UserField.PASS_HASH))
                .withRole(Role.getById(rs.getInt(MySqlConstant.UserField.ROLE_ID)))
                .build();
    }
}
