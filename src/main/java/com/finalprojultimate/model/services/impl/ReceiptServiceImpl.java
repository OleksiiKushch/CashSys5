package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.util.DAOConstants;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.services.ReceiptService;
import com.finalprojultimate.model.services.util.Cart;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ReceiptServiceImpl implements ReceiptService {
    private static final Logger logger = Logger.getLogger(ReceiptServiceImpl.class);

    private final ReceiptDAO receiptDAO;

    private static class Holder {
        static final ReceiptServiceImpl INSTANCE = new ReceiptServiceImpl();
    }

    public static ReceiptServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public ReceiptServiceImpl() {
        DAOFactory.setDaoFactoryFCN(DAOConstants.MySqlDAOFactoryFCN);
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        assert daoFactory != null;
        receiptDAO = daoFactory.getReceiptDAO();
        receiptDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    public List<Receipt> getAll() {
        return receiptDAO.getAll();
    }

    @Override
    public void create(Receipt receipt, Cart cart) {
        Collection<Product> products = cart.getContainer().values();
        logger.info("products: " + products);
        receiptDAO.create(receipt.getUserId(), receipt.getChange(), receipt.getPayment().getId(), new ArrayList<>(products));
    }

    @Override
    public List<Receipt> getForPagination(int offset, int limit) {
        return receiptDAO.findReceipts(offset, limit);
    }

    @Override
    public int getCount() {
        return receiptDAO.getCountOfReceipts();
    }


}
