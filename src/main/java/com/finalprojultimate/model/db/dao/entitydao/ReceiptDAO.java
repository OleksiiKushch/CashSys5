package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptDAO extends EntityDAO<Receipt> {
    // transaction
    void create(int userId, BigDecimal change, int paymentId, List<Product> products) throws DaoException;
    void createReject(int rootReceiptId, int userId, List<Product> products) throws DaoException;
    List<Receipt> findReceipts(int offset, int limit) throws DaoException;
    int getCountOfReceipts() throws DaoException;
    ReceiptDetails getGlobalReceiptProperties();
    void setGlobalReceiptProperties(ReceiptDetails receiptProperties);
    void resetGlobalReceiptProperties();
    BigDecimal getSumReceiptById(int id);
    List<Product> getProductsByReceiptId(int id);
    ReceiptDetails getReceiptDetailsById(int id);
}
