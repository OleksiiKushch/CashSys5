package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptDAO extends EntityDAO<Receipt> {
    // transactions
    Receipt create(int userId, BigDecimal change, int paymentId, List<Product> products) throws DaoException;
    Receipt createReject(int rootReceiptId, int userId, List<Product> products) throws DaoException;

    List<Receipt> findReceiptsWithPaginationSortByNone(int offset, int limit) throws DaoException;
    List<Receipt> findReceiptsWithPaginationSortByDateTime(int offset, int limit) throws DaoException;
    int getCountOfReceipts() throws DaoException;
    List<Receipt> findReceiptsContainProduct(Product product) throws DaoException;
    ReceiptDetails getGlobalReceiptProperties();
    void setGlobalReceiptProperties(ReceiptDetails receiptProperties);
    void resetGlobalReceiptProperties();
    BigDecimal getSumReceiptById(int id);
    List<Product> getProductsByReceiptId(int id);
    ReceiptDetails getReceiptDetailsById(int id);
}
