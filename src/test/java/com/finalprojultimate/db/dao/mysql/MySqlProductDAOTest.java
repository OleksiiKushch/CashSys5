package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.DBInit;
import com.finalprojultimate.db.dao.DAOFactory;
import com.finalprojultimate.db.dao.connection.DirectConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.Product;
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
        DBInit.startUp();
    }

    @Before
    public void setUp() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());
    }

    @Test
    public void saveDeleteTest() throws DaoException {
        Product product = new Product.Builder()
                .withName("Сrisps LUX 430g.")
                .withPrice(new BigDecimal("2.1"))
                .withAmount(new BigDecimal("75"))
                .withBarcode("4602313509807")
                .withUnit(Product.Unit.PIECES)
                .build();
        productDAO.insert(product);
        assertEquals(1, productDAO.findProductsByBarcode("4602313509807").size());
        productDAO.delete(product);
        assertEquals(0, productDAO.findProductsByBarcode("4602313509807").size());
    }

    @Test
    public void updateTest() throws DaoException {
        Product pBefore = productDAO.findProductsByName("sugar").get(0);
        Product pAfter = new Product.Builder()
                .withId(pBefore.getId())
                .withName(pBefore.getName())
                .withPrice(pBefore.getPrice())
                .withAmount(new BigDecimal("450.000"))
                .withBarcode(pBefore.getBarcode())
                .withUnit(pBefore.getUnit())
                .build();
        productDAO.update(pAfter);
        assertEquals(new BigDecimal("450.000"), productDAO.findProductsByName("sugar").get(0).getAmount());
        productDAO.update(pBefore);
        assertEquals(new BigDecimal("500.500"), productDAO.findProductsByName("sugar").get(0).getAmount());
    }

    @Test
    public void getByIdTest() throws DaoException {
        assertEquals("comb ParallaX", productDAO.getById(2).getName());
    }

    @Test
    public void getAllTest() throws DaoException {
        List<Product> result = productDAO.getAll();
        assertEquals(8, result.size());
    }

    @Test
    public void findProductsByNameTest() throws DaoException {
        List<Product> result = productDAO.findProductsByName("lux");
        assertEquals(2, result.size());
    }

    @Test
    public void findProductsByBarcodeTest() throws DaoException {
        List<Product> result = productDAO.findProductsByBarcode("22");
        assertEquals(3, result.size());
        result = productDAO.findProductsByBarcode("320499");
        assertEquals(1, result.size());
    }
}