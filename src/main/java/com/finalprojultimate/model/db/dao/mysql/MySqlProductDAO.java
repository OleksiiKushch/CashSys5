package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import com.finalprojultimate.model.services.util.ReportBestProductByCountReceipt;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.finalprojultimate.model.db.dao.mysql.MySqlConstant.ProductQuery.*;
import static com.finalprojultimate.model.db.dao.util.LogMessage.*;
import static com.finalprojultimate.util.MessageKey.*;

public class MySqlProductDAO implements ProductDAO {

    private static final Logger logger = Logger.getLogger(MySqlProductDAO.class);

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
    public void insert(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(CREATE_PRODUCT,
                     Statement.RETURN_GENERATED_KEYS)) {
            mapProduct(ps, product);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    product.setId(rs.getInt(1));
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
     * @param product concrete product
     * update product from DB, search by barcode product
     */
    @Override
    public void update(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(UPDATE_PRODUCT)) {
            mapProduct(ps, product);
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_UPDATE_ROW_TO_DATABASE,
                    UPDATE_ROW_TO_DATABASE_LOG_MSG, getClass());
        }
    }

    /**
     *
     * @param product concrete product
     * remove product from DB, search by id product
     */
    @Override
    public void delete(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(DELETE_PRODUCT_BY_BARCODE)) {
            ps.setInt(1, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_DELETE_ROW_FROM_DATABASE,
                    DELETE_ROW_FROM_DATABASE_LOG_MSG, getClass());
        }
    }

    @Override
    public Product getById(int id) throws DaoException {
        Product result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(GET_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapProduct(rs);
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
    public List<Product> getAll() throws DaoException {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(GET_ALL_PRODUCTS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapProduct(rs));
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
    public List<Product> findProductsByName(String pattern) throws DaoException {
        return findProductsByQuery(FIND_PRODUCTS_BY_NAME, pattern);
    }

    @Override
    public List<Product> findProductsByBarcode(String pattern) throws DaoException {
        return findProductsByQuery(FIND_PRODUCTS_BY_BARCODE, pattern);
    }

    /**
     * find a specific number of products, sorting them by the number of receipts <b>per month</b>
     *
     * @param limit limit (count) of products found
     * @return List of objects utility service class ReportBestProductsByCountReceipt
     * @throws DaoException specific exception
     */
    @Override
    public List<ReportBestProductByCountReceipt> findBestProductsByCountReceiptForTheLastMonth(int limit)
            throws DaoException {
        List<ReportBestProductByCountReceipt> result = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps
                = con.prepareStatement(FIND_BEST_PRODUCTS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH)) {
            ps.setInt(1, limit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapReportBestProductByCountReceipt(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_FIND_BEST_PRODUCTS_BY_COUNT_RECEIPT_FOR_THE_LAST_MONTH_FROM_DATABASE,
                    FIND_BEST_PRODUCTS_BY_COUNT_RECEIPT_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public List<Product> findProductsByIds(Set<Integer> ids) {
        List<Product> result = new ArrayList<>();
        for (Integer id : ids) {
            result.add(getById(id));
        }
        return result;
    }

    @Override
    public List<Product> findProductsWithPaginationSortByNone(int offset, int limit) throws DaoException {
        return findProductsWithPaginationSortByQuery(FIND_PRODUCTS_SORT_BY_NONE, offset, limit);
    }

    @Override
    public List<Product> findProductsWithPaginationSortByName(int offset, int limit) throws DaoException {
        return findProductsWithPaginationSortByQuery(FIND_PRODUCTS_SORT_BY_NAME, offset, limit);
    }

    private List<Product> findProductsWithPaginationSortByQuery(String query, int offset, int limit)
            throws DaoException {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(mapProduct(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_FIND_PRODUCTS_WITH_PAGINATION_FROM_DATABASE,
                    FIND_PRODUCTS_WITH_PAGINATION_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    @Override
    public int getCountOfProducts() throws DaoException {
        return getCountByQuery(GET_COUNT_OF_PRODUCTS);
    }

    private List<Product> findProductsByQuery(String query, String pattern) throws DaoException {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, "%" + escapeForLike(pattern) + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapProduct(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException(ERROR_FIND_PRODUCTS_BY_QUERY_FROM_DATABASE,
                    FIND_PRODUCTS_BY_QUERY_FROM_DATABASE_LOG_MSG, getClass());
        }
        return result;
    }

    private void mapProduct(PreparedStatement ps, Product product) throws SQLException {
        int i = 0;
        ps.setString(++i, product.getName());
        ps.setBigDecimal(++i, product.getPrice());
        ps.setBigDecimal(++i, product.getAmount());
        ps.setString(++i, product.getBarcode());
        ps.setInt(++i, product.getUnit().getId());
    }

    /**
     * used this in the class MySqlReceiptDAO
     */
    public static Product mapProduct(ResultSet rs) throws SQLException {
        return new Product.Builder()
                .withId(rs.getInt(MySqlConstant.ProductField.ID))
                .withName(rs.getString(MySqlConstant.ProductField.NAME))
                .withPrice(rs.getBigDecimal(MySqlConstant.ProductField.PRICE))
                .withAmount(rs.getBigDecimal(MySqlConstant.ProductField.AMOUNT))
                .withBarcode(rs.getString(MySqlConstant.ProductField.BARCODE))
                .withUnit(Unit.getById(rs.getInt(MySqlConstant.ProductField.UNIT_ID)))
                .build();
    }

    private ReportBestProductByCountReceipt mapReportBestProductByCountReceipt(ResultSet rs) throws SQLException {
        return new ReportBestProductByCountReceipt(
                rs.getInt(MySqlConstant.ProductField.PRODUCT_ID),
                rs.getBigDecimal(MySqlConstant.ProductField.TOTAL_AMOUNT),
                rs.getBigDecimal(MySqlConstant.ProductField.TOTAL_SUM),
                rs.getInt(MySqlConstant.ProductField.COUNT)
        );
    }

    /**
     * replaces special characters of search input data for correct LIKE operator search
     *
     * @param param input data for searching
     * @return escaping input from special characters
     */
    public static String escapeForLike(String param) {
        return param.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }
}
