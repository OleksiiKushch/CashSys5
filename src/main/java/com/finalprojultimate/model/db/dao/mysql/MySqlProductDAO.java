package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
             PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.CREATE_PRODUCT,
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
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    /**
     *
     * @param product
     * update product from DB, search by barcode product
     */
    @Override
    public void update(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.UPDATE_PRODUCT)) {
            mapProduct(ps, product);
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    /**
     *
     * @param product
     * remove product from DB, search by id product
     */
    @Override
    public void delete(Product product) throws DaoException {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.DELETE_PRODUCT_BY_BARCODE)) {
            ps.setInt(1, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
    }

    @Override
    public Product getById(int id) throws DaoException {
        Product result = null;
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.GET_PRODUCT_BY_ID)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = mapProduct(rs);
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public List<Product> getAll() throws DaoException {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.GET_ALL_PRODUCTS)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    result.add(mapProduct(rs));
                }
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public List<Product> findProductsByName(String pattern) throws DaoException {
        return findProductsByQuery(MySqlConstant.ProductQuery.FIND_PRODUCTS_BY_NAME, pattern);
    }

    @Override
    public List<Product> findProductsByBarcode(String pattern) throws DaoException {
        return findProductsByQuery(MySqlConstant.ProductQuery.FIND_PRODUCTS_BY_BARCODE, pattern);
    }

    @Override
    public List<Product> findProducts(int offset, int limit) throws DaoException {
        List<Product> result = new ArrayList<>();
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstant.ProductQuery.FIND_PRODUCTS_WITH_PAGINATION)) {
            ps.setInt(1, limit);
            ps.setInt(2, offset);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(mapProduct(rs));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
    }

    @Override
    public int getCountOfProducts() throws DaoException {
        int result = 0;
        try (Connection con = getConnection(); Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(MySqlConstant.ProductQuery.GET_COUNT_OF_PRODUCTS)) {
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw generateException("", "", getClass()); // Good explanation of error
        }
        return result;
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
            throw generateException("", "", getClass()); // Good explanation of error
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

    private Product mapProduct(ResultSet rs) throws SQLException {
        return new Product.Builder()
                .withId(rs.getInt(MySqlConstant.ProductField.ID))
                .withName(rs.getString(MySqlConstant.ProductField.NAME))
                .withPrice(rs.getBigDecimal(MySqlConstant.ProductField.PRICE))
                .withAmount(rs.getBigDecimal(MySqlConstant.ProductField.AMOUNT))
                .withBarcode(rs.getString(MySqlConstant.ProductField.BARCODE))
                .withUnit(Unit.getById(rs.getInt(MySqlConstant.ProductField.UNIT_ID)))
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
