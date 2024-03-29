package com.finalprojultimate.model.service.impl;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Payment;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.util.Cart;
import com.finalprojultimate.util.Parameter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class ReceiptServiceTest {

    ReceiptService testReceiptService;

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Before
    public void setUp() throws Exception {
        Class<?> clazz = ReceiptServiceImpl.class;
        Object initReceiptServiceImpl = clazz.newInstance();

        Field f1 = initReceiptServiceImpl.getClass().getDeclaredField("receiptDAO");
        f1.setAccessible(true);

        DAOFactory daoFactory = new MySqlDAOFactory();
        ReceiptDAO receiptDAO = daoFactory.getReceiptDAO();
        receiptDAO.setConnectionBuilder(new DirectConnectionBuilder());

        f1.set(initReceiptServiceImpl, receiptDAO);

        testReceiptService = (ReceiptServiceImpl) initReceiptServiceImpl;
    }

    @Test
    public void receiptServiceInit() {
        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        assertNotNull(receiptService);
    }

    @Test
    public void getAll() {
        List<Receipt> result = testReceiptService.getAll();
        assertEquals(11, result.size());
    }

    @Test
    public void getById() {
        Receipt result = testReceiptService.getById(4);
        assertEquals("2.50", result.getChange().toString());
    }

    @Test
    public void createAndRejectTest() throws SQLException, URISyntaxException, IOException {
        Receipt testReceipt = new Receipt.Builder()
                .withChange(new BigDecimal("0.5"))
                .withPayment(Payment.CASH)
                .withUserId(2)
                .build();

        Cart testCart = new Cart();
        putProductsIntoCart(testCart);

        Receipt result = testReceiptService.create(testReceipt, testCart);

        assertEquals("0.50", result.getChange().toString());
        assertEquals("7.40000", testReceiptService.getSumReceiptById(result.getId()).toString());

        Receipt testRejectReceipt = new Receipt.Builder()
                .withUserId(6)
                .build();

        List<Product> rejectProduct = new ArrayList<>(testCart.getContainer().values());
        List<BigDecimal> rejectAmounts = new ArrayList<>();
        rejectAmounts.add(new BigDecimal("1"));
        rejectAmounts.add(new BigDecimal("2"));

        Receipt resultReject = testReceiptService.createReject(result.getId(), testRejectReceipt, rejectProduct, rejectAmounts);

        assertEquals("6.60000", testReceiptService.getSumReceiptById(resultReject.getId()).toString());

        DBInit.startUp();
    }

    private void putProductsIntoCart(Cart cart) {
        DAOFactory daoFactory = new MySqlDAOFactory();
        ProductDAO productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());

        Product testProduct1 = productDAO.getById(2);
        testProduct1.setAmount(new BigDecimal("1"));
        Product testProduct2 = productDAO.getById(3);
        testProduct2.setAmount(new BigDecimal("3"));

        cart.put(testProduct1.getId(), testProduct1);
        cart.put(testProduct2.getId(), testProduct2);

    }

    @Test
    public void getForPagination() {
        List<Receipt> result = testReceiptService.getForPagination(2, 2);
        assertEquals(2, result.size());
    }

    @Test
    public void getForPaginationSortByParameter() {
        List<Receipt> result = testReceiptService.getForPaginationSortByParameter(Parameter.NONE,2, 2);
        assertEquals("0.00", result.get(0).getChange().toString());
        assertEquals("2.50", result.get(1).getChange().toString());

        result = testReceiptService.getForPaginationSortByParameter(Parameter.DATE_TIME,2, 2);
        assertEquals("0.00", result.get(0).getChange().toString());
        assertEquals("2.50", result.get(1).getChange().toString());
    }

    @Test
    public void getCount() {
        int result = testReceiptService.getCount();
        assertEquals(11, result);
    }

    @Test
    public void getGlobalReceiptProperties() {
        ReceiptDetails result = testReceiptService.getGlobalReceiptProperties();
        assertEquals("ReceiptDetails{receiptId=0, rootReceiptId=0, organizationTaxIdNumber=7802870820, " +
                "nameOrganization='ТОВ \"Епіцентр К\"', addressTradePoint='м.Харків, вул.Героїв Праці, 9А', " +
                "vat=20.00, taxationSys='ОСН'}", result.toString());
    }

    @Test
    public void setAndResetGlobalReceiptProperties() {
        ReceiptDetails testReceiptDetails = testReceiptService.getGlobalReceiptProperties();
        assertEquals("ReceiptDetails{receiptId=0, rootReceiptId=0, organizationTaxIdNumber=7802870820, " +
                "nameOrganization='ТОВ \"Епіцентр К\"', addressTradePoint='м.Харків, вул.Героїв Праці, 9А', " +
                "vat=20.00, taxationSys='ОСН'}", testReceiptDetails.toString());

        testReceiptDetails.setVat(new BigDecimal("15.00"));
        testReceiptService.setGlobalReceiptProperties(testReceiptDetails);

        testReceiptDetails = testReceiptService.getGlobalReceiptProperties();

        assertEquals("ReceiptDetails{receiptId=0, rootReceiptId=0, organizationTaxIdNumber=7802870820, " +
                "nameOrganization='ТОВ \"Епіцентр К\"', addressTradePoint='м.Харків, вул.Героїв Праці, 9А', " +
                "vat=15.00, taxationSys='ОСН'}", testReceiptDetails.toString());

        testReceiptService.resetGlobalReceiptProperties();

        assertNull(testReceiptService.getGlobalReceiptProperties());

        testReceiptDetails.setVat(new BigDecimal("20.00"));

        testReceiptService.setGlobalReceiptProperties(testReceiptDetails);
    }

    @Test
    public void getSumReceiptById() {
        BigDecimal result = testReceiptService.getSumReceiptById(3);
        assertEquals("3.10000", result.toString());
    }

    @Test
    public void getProductsByReceiptId() {
        List<Product> result = testReceiptService.getProductsByReceiptId(3);
        assertEquals(
                "[Product{id=4, name='Pepsi Cola 1l', price=1.00, amount=2.000, unit=Unit{id=1, name='pieces'}, barcode='4634271223504'}, " +
                        "Product{id=7, name='ordinary sugar', price=1.10, amount=1.000, unit=Unit{id=2, name='kilogram'}, barcode='4657488712948'}]",
                result.toString());
    }

    @Test
    public void getReceiptDetails() {
        ReceiptDetails result = testReceiptService.getReceiptDetails(1);
        assertEquals(0, result.getRootReceiptId());
    }

}