package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.db.entity.Product;
import com.finalprojultimate.db.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlProductDAO implements ProductDAO {

    private static final Logger logger = LoggerFactory.getLogger(MySqlProductDAO.class);

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
    public void save(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ProductQuery.CREATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setBigDecimal(3, product.getAmount());
            ps.setString(4, product.getBarcode());
            ps.setInt(5, product.getUnit().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
    }

    /**
     *
     * @param tBefore - the product to be changed
     * @param tAfter - the product to be exchanged for the old one
     * update product from DB, search by barcode product
     */
    @Override
    public void update(Product tBefore, Product tAfter) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ProductQuery.UPDATE_PRODUCT)) {
            ps.setString(1, tAfter.getName());
            ps.setBigDecimal(2, tAfter.getPrice());
            ps.setBigDecimal(3, tAfter.getAmount());
            ps.setString(4, tAfter.getBarcode());
            ps.setInt(5, tAfter.getUnit().getId());
            ps.setString(6, tBefore.getBarcode());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
    }

    /**
     *
     * @param product
     * remove product from DB, search by barcode product
     */
    @Override
    public void delete(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ProductQuery.DELETE_PRODUCT_BY_BARCODE)) {
            ps.setString(1, product.getBarcode());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
    }

    @Override
    public Product getById(int id) throws DaoException {
        Product result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ProductQuery.GET_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapProduct(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
        return result;
    }

    @Override
    public List<Product> getAll() throws DaoException {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ProductQuery.GET_ALL_PRODUCTS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapProduct(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DaoException("", e); // Good explanation of error
        }
        return result;
    }

    @Override
    public List<Product> findProductsByName(String pattern) throws DaoException {
        return findProductsByQuery(MySqlConstants.ProductQuery.FIND_PRODUCTS_BY_NAME, pattern);
    }

    @Override
    public List<Product> findProductsByBarcode(String pattern) throws DaoException {
        return findProductsByQuery(MySqlConstants.ProductQuery.FIND_PRODUCTS_BY_BARCODE, pattern);
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
            throw new DaoException("", e); // Good explanation of error
        }
        return result;
    }

    private Product mapProduct(ResultSet rs) throws SQLException {
        return new Product.Builder()
                .withId(rs.getInt(MySqlConstants.ProductField.ID))
                .withName(rs.getString(MySqlConstants.ProductField.NAME))
                .withPrice(rs.getBigDecimal(MySqlConstants.ProductField.PRICE))
                .withAmount(rs.getBigDecimal(MySqlConstants.ProductField.AMOUNT))
                .withBarcode(rs.getString(MySqlConstants.ProductField.BARCODE))
                .withUnit(Product.Unit.getById(rs.getInt(MySqlConstants.ProductField.UNIT_ID)))
                .build();
    }

    /**
     * replaces special characters of search input data for correct LIKE operator search
     *
     * @param param - input data for searching
     * @return - escaping input from special characters
     */

    public static String escapeForLike(String param) {
        return param.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }
}
