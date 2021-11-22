package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.ReceiptQuery.*;

public class MySqlReceiptDAO implements ReceiptDAO {

    private static final Logger logger = Logger.getLogger(MySqlReceiptDAO.class);

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
             PreparedStatement ps = con.prepareStatement(CREATE_RECEIPT,
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
             PreparedStatement ps = con.prepareStatement(UPDATE_RECEIPT)) {
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
             PreparedStatement ps = con.prepareStatement(DELETE_RECEIPT_BY_ID)) {
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
             PreparedStatement ps = con.prepareStatement(GET_RECEIPT_BY_ID)) {
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
             PreparedStatement ps = con.prepareStatement(GET_ALL_RECEIPTS)) {
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

    /**
     * Transaction that includes three main requests
     *  - creation of a new receipt
     *  - set receipt details
     *  - checking the availability of products in the stock and a corresponding reduction in their amount in the stock
     *  - insert the table receipt_has_product according to the list of products in the cart
     *
     * @param userId - id of the user (cashier) who request
     * @param change - sum change
     * @param paymentId - id of the payment type
     * @param products - list of products in the cart
     * @throws DaoException - specific dao exception
     */
    @Override
    public void create(int userId, BigDecimal change, int paymentId, List<Product> products) throws DaoException {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);

            int idCreatedReceipt = createNewReceipt(con, change, paymentId, userId);

            setReceiptDetails(con, idCreatedReceipt);

            processingAmountProducts(con, products);

            insertReceiptHasProduct(con, idCreatedReceipt, products);

            con.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            assert con != null;
            rollback(con);
            throw generateException("", "", getClass()); // Good explanation of error
        } finally {
            assert con != null;
            setAutoCommit(con);
            close(con);
        }
    }

    @Override
    public List<Receipt> findReceipts(int offset, int limit) throws DaoException {
        List<Receipt> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(FIND_RECEIPTS_WITH_PAGINATION)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(mapReceipt(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public int getCountOfReceipts() throws DaoException {
        int result = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_COUNT_OF_RECEIPTS)) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public ReceiptDetails getGlobalReceiptProperties() {
        ReceiptDetails receiptDetails = null;
        try (Connection con = getConnection(); Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(GET_GLOBAL_RECEIPT_PROPERTIES)) {
            if (rs.next()) {
                receiptDetails = mapGlobalReceiptProperties(rs);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return receiptDetails;
    }

    @Override
    public void setGlobalReceiptProperties(ReceiptDetails receiptDetails) {
        try (Connection con = getConnection();
             CallableStatement cs = con.prepareCall(SET_GLOBAL_RECEIPT_PROPERTIES)) {
            mapReceiptDetails(cs, receiptDetails);
            cs.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    @Override
    public void resetGlobalReceiptProperties() {
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(RESET_GLOBAL_RECEIPT_PROPERTIES);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    private void mapReceipt(PreparedStatement ps, Receipt receipt) throws SQLException {
        int i = 0;
        ps.setBigDecimal(++i, receipt.getChange());
        ps.setInt(++i, receipt.getPayment().getId());
        ps.setInt(++i, receipt.getUserId());
        ps.setInt(++i, receipt.getStatus().getId());
    }

    private void mapReceipt(PreparedStatement ps, BigDecimal change, int paymentId, int userId) throws SQLException {
        int i = 0;
        ps.setBigDecimal(++i, change);
        ps.setInt(++i, paymentId);
        ps.setInt(++i, userId);
        ps.setInt(++i, Status.NORMAL.getId());
    }

    private void mapReceiptHasProduct(PreparedStatement ps, int receiptId, int productId, BigDecimal price, BigDecimal amount)
            throws SQLException {
        int i = 0;
        ps.setInt(++i, receiptId);
        ps.setInt(++i, productId);
        ps.setBigDecimal(++i, price);
        ps.setBigDecimal(++i, amount);
    }

    private Receipt mapReceipt(ResultSet rs) throws SQLException {
        return new Receipt.Builder()
                .withId(rs.getInt(MySqlConstant.ReceiptField.ID))
                .withDateTime(rs.getTimestamp(MySqlConstant.ReceiptField.DATE_TIME).toLocalDateTime())
                .withChange(new BigDecimal(rs.getString(MySqlConstant.ReceiptField.CHANGE)))
                .withPayment(Payment.getById(rs.getInt(MySqlConstant.ReceiptField.PAYMENT_ID)))
                .withUserId(rs.getInt(MySqlConstant.ReceiptField.USER_ID))
                .withStatus(Status.getById(rs.getInt(MySqlConstant.ReceiptField.STATUS_ID)))
                .build();
    }

    private ReceiptDetails mapGlobalReceiptProperties(ResultSet rs) throws SQLException {
        return new ReceiptDetails.Builder()
                .withOrganizationTaxIdNumber(rs.getLong(MySqlConstant.ReceiptField.ORGANIZATION_TAX_ID_NUMBER))
                .withNameOrganization(rs.getString(MySqlConstant.ReceiptField.NAME_ORGANIZATION))
                .withAddressTradePoint(rs.getString(MySqlConstant.ReceiptField.ADDRESS_TRADE_POINT))
                .withVat(rs.getBigDecimal(MySqlConstant.ReceiptField.VAT))
                .withTaxationSys(rs.getString(MySqlConstant.ReceiptField.TAXATION_SYS))
                .build();
    }

    private void mapReceiptDetails(CallableStatement cs, ReceiptDetails receiptDetails) throws SQLException  {
        int i = 0;
        mapReceiptProperties(cs, receiptDetails, i);
    }

    private void mapReceiptDetails(PreparedStatement ps, int idCreatedReceipt, ReceiptDetails receiptDetails) throws SQLException {
        int i = 0;
        ps.setInt(++i, idCreatedReceipt);
        mapReceiptProperties(ps, receiptDetails, i);
    }

    private void mapReceiptProperties(PreparedStatement ps, ReceiptDetails receiptDetails, int i) throws SQLException {
        ps.setLong(++i, receiptDetails.getOrganizationTaxIdNumber());
        ps.setString(++i, receiptDetails.getNameOrganization());
        ps.setString(++i, receiptDetails.getAddressTradePoint());
        ps.setBigDecimal(++i, receiptDetails.getVat());
        ps.setString(++i, receiptDetails.getTaxationSys());
    }

    private int createNewReceipt(Connection con, BigDecimal change, int paymentId, int userId)
            throws SQLException {
        int idCreatedReceipt = -1;
        try (PreparedStatement ps = con.prepareStatement(CREATE_RECEIPT,
                Statement.RETURN_GENERATED_KEYS)) {
            mapReceipt(ps, change, paymentId, userId);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    idCreatedReceipt = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SQLException();
        }
        return idCreatedReceipt;
    }

    private void setReceiptDetails(Connection con, int idCreatedReceipt) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SET_RECEIPT_DETAILS)) {
            ReceiptDetails receiptDetails = getGlobalReceiptProperties();
            logger.info(receiptDetails.toString());
            mapReceiptDetails(ps, idCreatedReceipt, receiptDetails);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SQLException();
        }
    }

    private void processingAmountProducts(Connection con, List<Product> products) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.GET_AMOUNT_PRODUCT_IN_STOCK_BY_ID)) {
            for (Product product : products) {
                ps.setInt(1, product.getId());
                BigDecimal amountInStock = new BigDecimal("0");
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        amountInStock = rs.getBigDecimal(MySqlConstant.ProductField.AMOUNT);
                    }
                }
                if (amountInStock.compareTo(product.getAmount()) >= 0) {
                    updateAmountProductOnStock(con, product, amountInStock);
                } else {
                    throw new SQLException();
                }
            }
        }
    }

    private void updateAmountProductOnStock(Connection con, Product product, BigDecimal amountInStock) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.UPDATE_AMOUNT_PRODUCT_IN_STOCK_BY_ID)) {
            ps.setBigDecimal(1, amountInStock.subtract(product.getAmount()));
            ps.setInt(2, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SQLException();
        }
    }

    private void insertReceiptHasProduct(Connection con, int receiptId, List<Product> products) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(INSERT_RECEIPT_HAS_PRODUCT)) {
            for (Product product : products) {
                mapReceiptHasProduct(ps, receiptId, product.getId(), product.getPrice(), product.getAmount());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SQLException();
        }
    }
}
