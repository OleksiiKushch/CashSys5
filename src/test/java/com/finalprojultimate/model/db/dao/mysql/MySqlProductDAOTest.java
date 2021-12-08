package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MySqlProductDAOTest {

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
    public void insertAndDeleteTest() throws DaoException {
        Product product = new Product.Builder()
                .withName("Сrisps LUX 430g.")
                .withPrice(new BigDecimal("2.1"))
                .withAmount(new BigDecimal("75"))
                .withBarcode("4602313509807")
                .withUnit(Unit.PIECES)
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

    @Test
    public void getCountOfProducts() {
        int result = productDAO.getCountOfProducts();
        assertEquals(8, result);
    }

    @Test
    public void findProductsWithPaginationSortByNone() {
        List<Product> result = productDAO.findProductsWithPaginationSortByNone(2, 3);
        assertEquals(
                "[Product{id=3, name='pencil LUXON', price=0.80, amount=271.000, unit=Unit{id=1, name='pieces'}, barcode='4611366728476'}, " +
                "Product{id=4, name='Pepsi Cola 1l', price=1.00, amount=110.000, unit=Unit{id=1, name='pieces'}, barcode='4634271223504'}, " +
                "Product{id=5, name='Coca Cola 1.5l', price=1.40, amount=64.000, unit=Unit{id=1, name='pieces'}, barcode='3204992254639'}]",
                result.toString());
    }

    @Test
    public void findProductsWithPaginationSortByName() {
        List<Product> result = productDAO.findProductsWithPaginationSortByName(2, 3);
        assertEquals(
                "[Product{id=8, name='Fanta Jingle', price=1.10, amount=140.800, unit=Unit{id=3, name='litre'}, barcode='4411164778032'}, " +
                        "Product{id=7, name='ordinary sugar', price=1.10, amount=500.500, unit=Unit{id=2, name='kilogram'}, barcode='4657488712948'}, " +
                        "Product{id=6, name='pen JoJ', price=3.80, amount=189.000, unit=Unit{id=1, name='pieces'}, barcode='0601762649669'}]",
                result.toString());
    }
}