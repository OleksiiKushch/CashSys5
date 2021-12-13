package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.*;
import com.finalprojultimate.model.entity.user.User;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.ReceiptQuery.*;
import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.UserQuery.FIND_USERS_SORT_BY_EMAIL;
import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.UserQuery.FIND_USERS_SORT_BY_NONE;
import static com.finalprojultimate.model.db.dao.util.LogMessage.*;
import static com.finalprojultimate.util.MessageKey.*;

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
            throw generateException(ERROR_INSERT_ROW_TO_DATABASE,
                    INSERT_ROW_TO_DATABASE_LOG_MSG, getClass());
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
            throw generateException(ERROR_UPDATE_ROW_TO_DATABASE,
                    UPDATE_ROW_TO_DATABASE_LOG_MSG, getClass());
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
            throw generateException(ERROR_DELETE_ROW_FROM_DATABASE,
                    DELETE_ROW_FROM_DATABASE_LOG_MSG, getClass());
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
            throw generateException(ERROR_GETTING_ROW_FROM_DATABASE,
                    GETTING_ROW_FROM_DATABASE_LOG_MSG, getClass());
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
            throw generateException(ERROR_GETTING_ALL_ROWS_FROM_DATABASE,
                    GETTING_ALL_ROWS_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    /**
     * Transaction that includes four main requests
     * <ul>
     *     <li>creation of a new receipt</li>
     *     <li>set receipt details</li>
     *     <li>checking the availability of products in the stock and a corresponding reduction in their amount in the stock</li>
     *     <li>insert the table receipt_has_product according to the list of products in the cart</li>
     * </ul>
     *
     * @param userId id of the user (cashier) who request
     * @param change sum change
     * @param paymentId id of the payment type
     * @param products list of products in the cart
     * @throws DaoException specific dao exception
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
            rollback(con);
            throw generateException(ERROR_CREATE_RECEIPT_IN_DATABASE,
                    CREATE_RECEIPT_IN_DATABASE_LOG_MSG, getClass());
        } finally {
            close(con);
        }
    }

    /**
     * Transaction that includes four main requests
     * <ul>
     *     <li>creation of a new reject receipt</li>
     *     <li>copy and set receipt details (copy from root receipt (receipt that is being rejecting))</li>
     *     <li>processing amount of products in the root receipt (update amount products in the root receipt)</li>
     *     <li>insert the table receipt_has_product according to the list of products that is being rejecting</li>
     * </ul>
     *
     * @param rootReceiptId receipt id that is being rejecting
     * @param userId id of the user (senior cashier) who request
     * @param products list of products in the receipt that is being rejecting
     * @throws DaoException specific dao exception
     */
    @Override
    public void createReject(int rootReceiptId, int userId, List<Product> products) throws DaoException {
        Connection con = null;
        try {
            con = getConnection();
            con.setAutoCommit(false);

            int idCreatedRejectReceipt = createNewRejectReceipt(con, userId);

            ReceiptDetails receiptDetails = getReceiptDetailsById(rootReceiptId);
            if (receiptDetails != null) {
                receiptDetails.setRootReceiptId(rootReceiptId);
                setReceiptDetails(con, idCreatedRejectReceipt, receiptDetails);
            }

            for (Product product : products) {
                processingRejectReceipt(con, rootReceiptId, product.getId(), product.getAmount());
            }

            insertReceiptHasProduct(con, idCreatedRejectReceipt, products);

            con.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            rollback(con);
            throw generateException(ERROR_CREATE_REJECT_RECEIPT_IN_DATABASE,
                    CREATE_REJECT_RECEIPT_IN_DATABASE_LOG_MSG, getClass());
        } finally {
            close(con);
        }
    }

    @Override
    public List<Receipt> findReceiptsWithPaginationSortByNone(int offset, int limit) throws DaoException {
        return findReceiptsWithPaginationSortByQuery(FIND_RECEIPTS_SORT_BY_NONE, offset, limit);
    }

    @Override
    public List<Receipt> findReceiptsWithPaginationSortByDateTime(int offset, int limit) throws DaoException {
        return findReceiptsWithPaginationSortByQuery(FIND_RECEIPTS_SORT_BY_DATE_TIME, offset, limit);
    }

    private List<Receipt> findReceiptsWithPaginationSortByQuery(String query, int offset, int limit) throws DaoException {
        List<Receipt> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(mapReceipt(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_FIND_RECEIPTS_WITH_PAGINATION_FROM_DATABASE,
                    FIND_RECEIPTS_WITH_PAGINATION_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public int getCountOfReceipts() throws DaoException {
        return getCountByQuery(GET_COUNT_OF_RECEIPTS);
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
            throw generateException(ERROR_GET_GLOBAL_RECEIPT_PROPERTIES_FROM_DATABASE,
                    GET_GLOBAL_RECEIPT_PROPERTIES_FROM_DATABASE_LOG_MSG, getClass());
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
            throw generateException(ERROR_SET_GLOBAL_RECEIPT_PROPERTIES_TO_DATABASE,
                    SET_GLOBAL_RECEIPT_PROPERTIES_TO_DATABASE_LOG_MSG, getClass());
        }
    }

    @Override
    public void resetGlobalReceiptProperties() {
        try (Connection con = getConnection(); Statement stmt = con.createStatement()) {
            stmt.executeUpdate(RESET_GLOBAL_RECEIPT_PROPERTIES);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_RESET_GLOBAL_RECEIPT_PROPERTIES_IN_DATABASE,
                    RESET_GLOBAL_RECEIPT_PROPERTIES_IN_DATABASE_LOG_MSG, getClass());
        }
    }

    /**
     *
     * @param id id receipt
     * @return total sum receipt
     */
    @Override
    public BigDecimal getSumReceiptById(int id) {
        BigDecimal result = new BigDecimal("0");
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(GET_SUM_RECEIPT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getBigDecimal(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GET_SUM_RECEIPT_BY_ID_FROM_DATABASE,
                    GET_SUM_RECEIPT_BY_ID_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    /**
     *
     * @param id id receipt
     * @return the list of products contained in the receipt
     */
    @Override
    public List<Product> getProductsByReceiptId(int id) {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(GET_PRODUCTS_BY_RECEIPT_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(MySqlProductDAO.mapProduct(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GET_PRODUCTS_BY_RECEIPT_ID_FROM_DATABASE,
                    GET_PRODUCTS_BY_RECEIPT_ID_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public ReceiptDetails getReceiptDetailsById(int id) {
        ReceiptDetails result = null;
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(GET_RECEIPT_DETAILS_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapReceiptDetails(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_GET_RECEIPT_DETAILS_BY_ID_FROM_DATABASE,
                    GET_RECEIPT_DETAILS_BY_ID_FROM_DATABASE_LOG_MSG, getClass());
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

    /**
     * mapping creating new receipt with status 'normal'
     *
     * @param ps PreparedStatement
     * @param change change of a receipt
     * @param paymentId id payment type
     * @param userId cashier id
     * @throws SQLException sql exception
     */
    private void mapReceipt(PreparedStatement ps, BigDecimal change, int paymentId, int userId) throws SQLException {
        int i = 0;
        ps.setBigDecimal(++i, change);
        ps.setInt(++i, paymentId);
        ps.setInt(++i, userId);
        ps.setInt(++i, Status.NORMAL.getId());
    }

    /**
     * mapping reject receipt with status 'rejected' and default settings (change of a receipt = 0 and payment type 'electronic')
     *
     * @param ps PreparedStatement
     * @param userId senior cashier id
     * @throws SQLException sql exception
     */
    private void mapRejectReceipt(PreparedStatement ps, int userId) throws SQLException {
        int i = 0;
        ps.setBigDecimal(++i, new BigDecimal("0"));
        ps.setInt(++i, Payment.ELECTRONIC.getId());
        ps.setInt(++i, userId);
        ps.setInt(++i, Status.REJECTED.getId());
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
        return mapReceiptProperties(rs);
    }

    private ReceiptDetails mapReceiptDetails(ResultSet rs) throws SQLException {
        ReceiptDetails result = mapReceiptProperties(rs);
        result.setReceiptId(rs.getInt(MySqlConstant.ReceiptField.RECEIPT_ID));
        result.setRootReceiptId(rs.getInt(MySqlConstant.ReceiptField.ROOT_RECEIPT_ID));
        return result;
    }

    private ReceiptDetails mapReceiptProperties(ResultSet rs) throws SQLException {
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
        ps.setInt(++i, receiptDetails.getRootReceiptId());
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

    private int createNewRejectReceipt(Connection con, int userId)
            throws SQLException {
        int idCreatedReceipt = -1;
        try (PreparedStatement ps = con.prepareStatement(CREATE_RECEIPT,
                Statement.RETURN_GENERATED_KEYS)) {
            mapRejectReceipt(ps, userId);
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
            mapReceiptDetails(ps, idCreatedReceipt, receiptDetails);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new SQLException();
        }
    }

    private void setReceiptDetails(Connection con, int idCreatedReceipt, ReceiptDetails receiptDetails) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(SET_RECEIPT_DETAILS)) {
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

    /**
     * delete row from receipt_has_product table if amount of rejected products (@param amount)
     * is equal to amount of products in the receipt (in receipt_has_product table)
     * else reduces amount of products in the receipt by the appropriate amount (@param amount)
     *
     * @param con database connection
     * @param rootReceiptId receipt id that is being rejecting
     * @param productId id rejected product
     * @param amount amount of rejected products
     */
    private void processingRejectReceipt(Connection con, int rootReceiptId, int productId, BigDecimal amount) {
        try (CallableStatement cs = con.prepareCall(PROCESSING_REJECT_RECEIPT)) {
            int i = 0;
            cs.setInt(++i, rootReceiptId);
            cs.setInt(++i, productId);
            cs.setBigDecimal(++i, amount);
            cs.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_PROCESSING_REJECT_RECEIPT_IN_DATABASE,
                    PROCESSING_REJECT_RECEIPT_IN_DATABASE_LOG_MSG, getClass());
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
