package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.dao.connection.ConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.entity.Product;
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
    public void create(Product product) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(MySqlConstants.ProductQuery.CREATE_PRODUCT)) {
            ps.setString(1, product.getName());
            ps.setBigDecimal(2, product.getPrice());
            ps.setBigDecimal(3, product.getAmount());
            ps.setString(4, product.getBarcode());
            ps.setString(5, product.getUnit().getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            // throw new Exception("Good explanation of error", e);
        }
    }

    @Override
    public List<Product> getAll() {
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
            // throw new Exception("Good explanation of error", e);
        }
        return result;
    }

    @Override
    public List<Product> findProductsByName(String pattern) {
        return findProductsByQuery(MySqlConstants.ProductQuery.FIND_PRODUCTS_BY_NAME, pattern);
    }

    @Override
    public List<Product> findProductsByBarcode(String pattern) {
        return findProductsByQuery(MySqlConstants.ProductQuery.FIND_PRODUCTS_BY_BARCODE, pattern);
    }

    private List<Product> findProductsByQuery(String query, String pattern) {
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
            // throw new Exception("Good explanation of error", e);
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
                .withUnit(Product.Unit.getById(rs.getInt(MySqlConstants.ProductField.UNIT)))
                .build();
    }

    public static String escapeForLike(String param) {
        return param.replace("!", "!!")
                .replace("%", "!%")
                .replace("_", "!_")
                .replace("[", "![");
    }
}
