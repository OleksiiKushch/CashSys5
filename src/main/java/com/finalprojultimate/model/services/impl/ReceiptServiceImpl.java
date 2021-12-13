package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ReceiptDAO;
import com.finalprojultimate.model.db.dao.util.DAOConstants;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.services.ReceiptService;
import com.finalprojultimate.model.services.util.Cart;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.finalprojultimate.util.Parameter.DATE_TIME;

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
        DAOFactory.setDaoFactoryFCN(DAOConstants.MY_SQL_DAO_FACTORY_FCN);
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        receiptDAO = daoFactory.getReceiptDAO();
        receiptDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    public List<Receipt> getAll() {
        return receiptDAO.getAll();
    }

    @Override
    public Receipt getById(int id) {
        return receiptDAO.getById(id);
    }

    @Override
    public void create(Receipt receipt, Cart cart) {
        Collection<Product> products = cart.getContainer().values();
        receiptDAO.create(receipt.getUserId(), receipt.getChange(), receipt.getPayment().getId(), new ArrayList<>(products));
    }

    @Override
    public void createReject(int rootReceiptId, Receipt newReceipt, List<Product> products, List<BigDecimal> amounts) {
        for (int i = 0; i < products.size(); i++) {
            products.get(i).setAmount(amounts.get(i));
        }
        receiptDAO.createReject(rootReceiptId, newReceipt.getUserId(), products);
    }

    @Override
    public List<Receipt> getForPagination(int offset, int limit) {
        return receiptDAO.findReceiptsWithPaginationSortByNone(offset, limit);
    }

    /**
     * return receipts from the database in the range [(offset - 1) * limit; offset * limit] pre-sorted by parameter or not (none)
     *
     * @param sortParameter parameter sorting [none, dateTime]
     * @param offset number of page at pagination
     * @param limit number of return receipts (size List)
     * @return list of receipts
     */
    @Override
    public List<Receipt> getForPaginationSortByParameter(String sortParameter, int offset, int limit) {
        if (sortParameter.equals(DATE_TIME)) {
            return receiptDAO.findReceiptsWithPaginationSortByDateTime(offset, limit);
        } else {
            return getForPagination(offset, limit);
        }
    }

    @Override
    public int getCount() {
        return receiptDAO.getCountOfReceipts();
    }

    @Override
    public ReceiptDetails getGlobalReceiptProperties() {
        return receiptDAO.getGlobalReceiptProperties();
    }

    @Override
    public void setGlobalReceiptProperties(ReceiptDetails receiptProperties) {
        receiptDAO.setGlobalReceiptProperties(receiptProperties);
    }

    @Override
    public void resetGlobalReceiptProperties() {
        receiptDAO.resetGlobalReceiptProperties();
    }

    @Override
    public BigDecimal getSumReceiptById(int id) {
        return receiptDAO.getSumReceiptById(id);
    }

    @Override
    public List<Product> getProductsByReceiptId(int id) {
        return receiptDAO.getProductsByReceiptId(id);
    }

    @Override
    public ReceiptDetails getReceiptDetails(int id) {
        return receiptDAO.getReceiptDetailsById(id);
    }

}
