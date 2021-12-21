package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.finalprojultimate.util.Parameter.RECEIPT_ID;

public class GetSeeReceiptDetailsCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetSeeReceiptDetailsCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String receiptId = request.getParameter(RECEIPT_ID);
        int id = Integer.parseInt(receiptId);

        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        List<Product> products = receiptService.getProductsByReceiptId(id);
        Receipt receipt = receiptService.getById(id);
        ReceiptDetails receiptDetails = receiptService.getReceiptDetails(id);

        request.setAttribute(Attribute.PRODUCTS, products);
        request.setAttribute(Attribute.RECEIPT, receipt);
        request.setAttribute(Attribute.RECEIPT_DETAILS, receiptDetails);


        return Page.SEE_RECEIPT_DETAILS_PAGE;
    }
}
