package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.DBInit;
import com.finalprojultimate.db.dao.DAOFactory;
import com.finalprojultimate.db.dao.connection.DirectConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.Receipt;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class MySqlReceiptDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(MySqlReceiptDAOTest.class);

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
                .withPayment(Receipt.Payment.CASH)
                .withUserId(4)
                .withStatus(Receipt.Status.NORMAL)
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
}