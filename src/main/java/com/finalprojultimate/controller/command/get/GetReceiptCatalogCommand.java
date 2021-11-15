package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.services.ReceiptService;
import com.finalprojultimate.model.services.impl.ProductServiceImpl;
import com.finalprojultimate.model.services.impl.ReceiptServiceImpl;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetReceiptCatalogCommand implements Command {
    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Receipt> receipts = receiptService.getAll();
        request.setAttribute(Attribute.RECEIPT_CATALOG, receipts);

        return Page.RECEIPT_CATALOG_PAGE;
    }
}
