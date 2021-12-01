package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.services.util.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptService {
    List<Receipt> getAll();
    Receipt getById(int id);
    void create(Receipt receipt, Cart cart);
    void createReject(int rootReceiptId, Receipt receipt, List<Product> products, List<BigDecimal> amounts);
    List<Receipt> getForPagination(int offset, int limit);
    int getCount();
    ReceiptDetails getGlobalReceiptProperties();
    void setGlobalReceiptProperties(ReceiptDetails receiptProperties);
    void resetGlobalReceiptProperties();
    BigDecimal getSumReceiptById(int id);
    List<Product> getProductsByReceiptId(int id);
    ReceiptDetails getReceiptDetails(int id);
}
