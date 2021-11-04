package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.DBInit;
import com.finalprojultimate.db.dao.DAOFactory;
import com.finalprojultimate.db.dao.connection.DirectConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.entity.Product;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlProductDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(MySqlProductDAOTest.class);

    ProductDAO productDAO;

    @BeforeClass
    public static void startUp() throws Exception {
        logger.info("Reset db");
        DBInit.startUp();
    }

    @Before
    public void setUp() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());
    }

    @Test
    public void createTest() {
        Product product = new Product.Builder()
                .withName("Сrisps LUX 430g.")
                .withPrice(new BigDecimal("2.1"))
                .withAmount(new BigDecimal("75"))
                .withBarcode("4602313509807")
                .withUnit(Product.Unit.PIECES)
                .build();
        productDAO.create(product);
        assertEquals(1, productDAO.findProductsByBarcode("4602313509807").size());
    }

    @Test
    public void getAllTest() {

    }

    @Test
    public void findProductByName() {
        List<Product> result = productDAO.findProductsByName("lux");
        assertEquals(2, result.size());
    }

    @Test
    public void findProductByBarcode() {
        List<Product> result = productDAO.findProductsByBarcode("11");
        assertEquals(8, result.size());
        result = productDAO.findProductsByBarcode("2");
        assertEquals(1, result.size());
    }
}