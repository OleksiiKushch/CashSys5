package com.finalprojultimate.model.service;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.service.util.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface ReceiptService {
    List<Receipt> getAll();
    Receipt getById(int id);
    Receipt create(Receipt receipt, Cart cart);
    Receipt createReject(int rootReceiptId, Receipt receipt, List<Product> products, List<BigDecimal> amounts);
    List<Receipt> getForPagination(int offset, int limit);
    List<Receipt> getForPaginationSortByParameter(String sortParameter, int offset, int limit);
    List<Receipt> findReceiptsContainProduct(Product product);
    int getCount();
    ReceiptDetails getGlobalReceiptProperties();
    void setGlobalReceiptProperties(ReceiptDetails receiptProperties);
    void resetGlobalReceiptProperties();
    BigDecimal getSumReceiptById(int id);
    List<Product> getProductsByReceiptId(int id);
    ReceiptDetails getReceiptDetails(int id);
}
