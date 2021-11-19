package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptDAO extends EntityDAO<Receipt> {
    // transaction
    void create(int userId, BigDecimal change, int paymentId, List<Product> products) throws DaoException;
    List<Receipt> findReceipts(int offset, int limit) throws DaoException;
    int getCountOfReceipts() throws DaoException;
}
