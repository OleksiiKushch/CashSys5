package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlReceiptDAO implements ReceiptDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySqlReceiptDAO.class);

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
    public void insert(Receipt receipt) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ReceiptQuery.CREATE_RECEIPT,
                     Statement.RETURN_GENERATED_KEYS)) {
            mapReceipt(ps, receipt);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    receipt.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    /**
     *
     * @param receipt
     * update receipt from DB, search by id receipt
     */
    @Override
    public void update(Receipt receipt) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ReceiptQuery.UPDATE_RECEIPT)) {
            mapReceipt(ps, receipt);
            ps.setInt(5, receipt.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    /**
     *
     * @param receipt
     * remove receipt from DB, search by id receipt
     */
    @Override
    public void delete(Receipt receipt) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ReceiptQuery.DELETE_RECEIPT_BY_ID)) {
            ps.setInt(1, receipt.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    @Override
    public Receipt getById(int id) throws DaoException {
        Receipt result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ReceiptQuery.GET_RECEIPT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapReceipt(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public List<Receipt> getAll() throws DaoException {
        List<Receipt> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ReceiptQuery.GET_ALL_RECEIPTS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapReceipt(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    private void mapReceipt(PreparedStatement ps, Receipt receipt) throws SQLException {
        int i = 0;
        ps.setBigDecimal(++i, receipt.getChange());
        ps.setInt(++i, receipt.getPayment().getId());
        ps.setInt(++i, receipt.getUserId());
        ps.setInt(++i, receipt.getStatus().getId());
    }

    private Receipt mapReceipt(ResultSet rs) throws SQLException {
        return new Receipt.Builder()
                .withId(rs.getInt(MySqlConstants.ReceiptField.ID))
                .withDateTime(rs.getTimestamp(MySqlConstants.ReceiptField.DATE_TIME).toLocalDateTime())
                .withChange(new BigDecimal(rs.getString(MySqlConstants.ReceiptField.CHANGE)))
                .withPayment(Receipt.Payment.getById(rs.getInt(MySqlConstants.ReceiptField.PAYMENT_ID)))
                .withUserId(rs.getInt(MySqlConstants.ReceiptField.USER_ID))
                .withStatus(Receipt.Status.getById(rs.getInt(MySqlConstants.ReceiptField.STATUS_ID)))
                .build();
    }
}
