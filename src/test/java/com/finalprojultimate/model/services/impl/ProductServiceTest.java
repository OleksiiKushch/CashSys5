package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.util.ReportBestProductByCountReceipt;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ProductServiceTest {

    ProductService testProductService;

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Before
    public void setUp() throws Exception {
        Class<?> clazz = ProductServiceImpl.class;
        Object initProductServiceImpl = clazz.newInstance();

        Field f1 = initProductServiceImpl.getClass().getDeclaredField("productDAO");
        f1.setAccessible(true);

        DAOFactory daoFactory = new MySqlDAOFactory();
        ProductDAO productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());

        f1.set(initProductServiceImpl, productDAO);

        testProductService = (ProductServiceImpl) initProductServiceImpl;
    }

    @Test
    public void productServiceInitTest() {
        ProductService productService = ProductServiceImpl.getInstance();
        assertNotNull(productService);
    }

    @Test
    public void getAllTest() {
        List<Product> products = testProductService.getAll();
        assertEquals(8, products.size());
    }

    @Test
    public void getCountTest() {
        int result = testProductService.getCount();
        assertEquals(8, result);
    }

    @Test
    public void createAndDeleteTest() {
        Product product = new Product.Builder()
                .withName("Сrisps LUX 430g.")
                .withPrice(new BigDecimal("2.1"))
                .withAmount(new BigDecimal("75"))
                .withBarcode("4602313509807")
                .withUnit(Unit.PIECES)
                .build();
        testProductService.create(product);
        assertEquals(9, testProductService.getCount());
        product.setId(9);
        testProductService.delete(product);
        assertEquals(8, testProductService.getCount());
    }

    @Test
    public void getByIdTest() {
        Product result = testProductService.getById(2);
        assertEquals("Product{id=2, name='comb ParallaX', price=5.00, amount=23.000, " +
                "unit=Unit{id=1, name='pieces'}, barcode='4605821233212'}", result.toString());
    }

    @Test
    public void updateTest() {
        Product product = testProductService.getById(2);
        product.setPrice(new BigDecimal("6"));
        testProductService.update(product);
        Product updateProduct = testProductService.getById(2);
        assertEquals("Product{id=2, name='comb ParallaX', price=6.00, amount=23.000, " +
                "unit=Unit{id=1, name='pieces'}, barcode='4605821233212'}", updateProduct.toString());

        updateProduct.setPrice(new BigDecimal("5"));
        testProductService.update(updateProduct);
    }

    @Test
    public void getBestProductsByCountReceiptForTheLastMonthTest() {
        List<ReportBestProductByCountReceipt> result = testProductService.getBestProductsByCountReceiptForTheLastMonth(2);
        assertEquals("[ReportBestProductByCountReceipt{productId=3, totalAmount=9.000, totalSum=7.20000, countReceipts=4}, " +
                "ReportBestProductByCountReceipt{productId=4, totalAmount=5.000, totalSum=5.00000, countReceipts=4}]",
                result.toString());
    }

    @Test
    public void getUsersByIdsTest() {
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(6);
        List<Product> result = testProductService.getProductsByIds(ids);
        assertEquals(
                "[Product{id=1, name='stapler LUXON', price=2.50, amount=70.000, " +
                        "unit=Unit{id=1, name='pieces'}, barcode='4613244322657'}, " +
                        "Product{id=6, name='pen JoJ', price=3.80, amount=189.000, " +
                        "unit=Unit{id=1, name='pieces'}, barcode='0601762649669'}]",
                result.toString());
    }

    @Test
    public void getForPaginationTest() {
        List<Product> result = testProductService.getForPagination(2, 3);
        assertEquals(
                "[Product{id=3, name='pencil LUXON', price=0.80, amount=271.000, unit=Unit{id=1, name='pieces'}, barcode='4611366728476'}, " +
                "Product{id=4, name='Pepsi Cola 1l', price=1.00, amount=110.000, unit=Unit{id=1, name='pieces'}, barcode='4634271223504'}, " +
                "Product{id=5, name='Coca Cola 1.5l', price=1.40, amount=64.000, unit=Unit{id=1, name='pieces'}, barcode='3204992254639'}]",
                result.toString());
    }

    @Test
    public void getForPaginationSortByParameterTest() {
        List<Product> result = testProductService.getForPaginationSortByParameter("none",2, 3);
        assertEquals(
                "[Product{id=3, name='pencil LUXON', price=0.80, amount=271.000, unit=Unit{id=1, name='pieces'}, barcode='4611366728476'}, " +
                        "Product{id=4, name='Pepsi Cola 1l', price=1.00, amount=110.000, unit=Unit{id=1, name='pieces'}, barcode='4634271223504'}, " +
                        "Product{id=5, name='Coca Cola 1.5l', price=1.40, amount=64.000, unit=Unit{id=1, name='pieces'}, barcode='3204992254639'}]",
                result.toString());

        result = testProductService.getForPaginationSortByParameter("name",2, 3);
        assertEquals(
                "[Product{id=8, name='Fanta Jingle', price=1.10, amount=140.800, unit=Unit{id=3, name='litre'}, barcode='4411164778032'}, " +
                        "Product{id=7, name='ordinary sugar', price=1.10, amount=500.500, unit=Unit{id=2, name='kilogram'}, barcode='4657488712948'}, " +
                        "Product{id=6, name='pen JoJ', price=3.80, amount=189.000, unit=Unit{id=1, name='pieces'}, barcode='0601762649669'}]",
                result.toString());
    }

    @Test
    public void findProductsByBarcodeTest() {
        List<Product> result = testProductService.findProductsByBarcode("22");
        assertEquals(3, result.size());
        result = testProductService.findProductsByBarcode("320499");
        assertEquals(1, result.size());
    }

    @Test
    public void findProductsByNameTest() {
        List<Product> result = testProductService.findProductsByName("lux");
        assertEquals(2, result.size());
    }

}