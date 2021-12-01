package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Payment;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.entity.receipt.Status;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MySqlReceiptDAOTest {

    ReceiptDAO receiptDAO;

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Before
    public void setUp() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        receiptDAO = daoFactory.getReceiptDAO();
        receiptDAO.setConnectionBuilder(new DirectConnectionBuilder());
    }

    @Test
    public void saveDeleteTest() throws DaoException {
        Receipt receipt = new Receipt.Builder()
                .withChange(new BigDecimal("0.45"))
                .withPayment(Payment.CASH)
                .withUserId(4)
                .withStatus(Status.NORMAL)
                .build();
        receiptDAO.insert(receipt);
        assertEquals(new BigDecimal("0.45"), receiptDAO.getById(12).getChange());
        receiptDAO.delete(receipt);
        assertNull(receiptDAO.getById(12));
    }

    @Test
    public void updateTest() throws DaoException {
        Receipt rBefore = receiptDAO.getById(3);
        Receipt rAfter = new Receipt.Builder()
                .withId(rBefore.getId())
                .withChange(new BigDecimal("12.6"))
                .withPayment(rBefore.getPayment())
                .withUserId(rBefore.getUserId())
                .withStatus(rBefore.getStatus())
                .build();
        receiptDAO.update(rAfter);
        assertEquals(new BigDecimal("12.60"), receiptDAO.getById(3).getChange());
        receiptDAO.update(rBefore);
        assertEquals(new BigDecimal("0.00"), receiptDAO.getById(3).getChange());
    }

    @Test
    public void getByIdTest() throws DaoException {
        assertEquals(new BigDecimal("12.00"), receiptDAO.getById(2).getChange());
    }

    @Test
    public void getAllTest() throws DaoException {
        List<Receipt> result = receiptDAO.getAll();
        assertEquals(11, result.size());
    }

    @Test
    public void create() throws SQLException, URISyntaxException, IOException {
        DAOFactory daoFactory = new MySqlDAOFactory();
        ProductDAO productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());

        List<Product> products = new ArrayList<>();
        Product product1 = productDAO.getById(1);
        BigDecimal testAmountProduct1 = product1.getAmount();
        product1.setAmount(new BigDecimal("3"));
        products.add(product1);
        Product product2 = productDAO.getById(3);
        product2.setAmount(new BigDecimal("1"));
        products.add(product2);
        receiptDAO.create(1, new BigDecimal("0"), 2, products);

        Receipt result = receiptDAO.getById(12);
        assertEquals("normal", result.getStatus().getName());
        assertEquals(testAmountProduct1.subtract(new BigDecimal("3")), productDAO.getById(1).getAmount());

        DBInit.startUp();
    }

    @Test
    public void createReject() throws SQLException, URISyntaxException, IOException {
        DAOFactory daoFactory = new MySqlDAOFactory();
        ProductDAO productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());

        List<Product> products = new ArrayList<>();
        Product product1 = productDAO.getById(1);
        product1.setAmount(new BigDecimal("1"));
        products.add(product1);
        receiptDAO.createReject(11, 6, products);

        Receipt result = receiptDAO.getById(13);
        assertEquals("rejected", result.getStatus().getName());

        DBInit.startUp();
    }

    @Test
    public void findReceipts() {
        List<Receipt> result = receiptDAO.findReceipts(2, 3);
        assertEquals(3, result.size());
    }

    @Test
    public void setAndResetGlobalReceiptProperties() {
        ReceiptDetails receiptDetails = new ReceiptDetails();
        receiptDetails.setOrganizationTaxIdNumber(7802870820L);
        receiptDetails.setNameOrganization("ТОВ \"Епіцентр К\"");
        receiptDetails.setAddressTradePoint("м.Харків, вул.Героїв Праці, 9А");
        receiptDetails.setVat(new BigDecimal("15.00"));
        receiptDetails.setTaxationSys("ОСН");

        receiptDAO.setGlobalReceiptProperties(receiptDetails);

        ReceiptDetails receiptDetailsTest = receiptDAO.getGlobalReceiptProperties();

        assertEquals("15.00", receiptDetailsTest.getVat().toString());

        receiptDAO.resetGlobalReceiptProperties();

        receiptDetailsTest = receiptDAO.getGlobalReceiptProperties();

        assertNull(receiptDetailsTest);

        receiptDetails.setVat(new BigDecimal("20.00"));

        receiptDAO.setGlobalReceiptProperties(receiptDetails);

    }

    @Test
    public void getSumReceiptById() {
        BigDecimal result = receiptDAO.getSumReceiptById(2);
        assertEquals("2.50000", result.toString());
    }

    @Test
    public void getProductsByReceiptId() {
        List<Product> result = receiptDAO.getProductsByReceiptId(3);
        assertEquals(2, result.size());
    }
}