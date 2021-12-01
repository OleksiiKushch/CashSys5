package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.ReceiptService;
import com.finalprojultimate.model.services.impl.ProductServiceImpl;
import com.finalprojultimate.model.services.impl.ReceiptServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.finalprojultimate.util.Attribute.LOGGED_USER;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class PostCreateRejectReceiptCommand implements Command {
    private static final Logger logger = Logger.getLogger(PostCreateRejectReceiptCommand.class);
    private static final String REJECT_RECEIPT_CREATE = "New reject receipt create successfully!";

    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();
    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String [] rejectProductIds = request.getParameterValues(REJECT_RECEIPT_ID);
        List<Product> products = new ArrayList<>();
        for (String id : rejectProductIds) {
            products.add(productService.getById(Integer.parseInt(id)));
        }

        String [] amountRejectProducts = request.getParameterValues(AMOUNT);
        List<BigDecimal> amounts = new ArrayList<>();
        for (String amount : amountRejectProducts) {
            amounts.add(new BigDecimal(amount));
        }

        Receipt newReceipt = new Receipt();
        User user = (User) request.getSession().getAttribute(LOGGED_USER);
        newReceipt.setUserId(user.getId());

        String rootReceiptId = request.getParameter(RECEIPT_ID);

        // transaction
        receiptService.createReject(Integer.parseInt(rootReceiptId), newReceipt, products, amounts);

        logger.info(REJECT_RECEIPT_CREATE);
        response.sendRedirect(CONTROLLER + "?command=" + SUCCESSFUL_CREATE_NEW_REJECT_RECEIPT);
        return REDIRECTED;
    }
}
