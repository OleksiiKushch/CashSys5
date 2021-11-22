package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.services.util.Cart;

import java.util.List;

public interface ReceiptService {
    List<Receipt> getAll();
    void create(Receipt receipt, Cart cart);
    List<Receipt> getForPagination(int offset, int limit);
    int getCount();
    ReceiptDetails getGlobalReceiptProperties();
    void setGlobalReceiptProperties(ReceiptDetails receiptProperties);
    void resetGlobalReceiptProperties();
}
