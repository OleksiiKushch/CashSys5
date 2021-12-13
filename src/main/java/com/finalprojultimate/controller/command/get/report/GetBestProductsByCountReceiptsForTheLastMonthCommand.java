package com.finalprojultimate.controller.command.get.report;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.impl.ProductServiceImpl;
import com.finalprojultimate.model.services.util.ReportBestProductByCountReceipt;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.finalprojultimate.util.Attribute.*;

public class GetBestProductsByCountReceiptsForTheLastMonthCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetBestProductsByCountReceiptsForTheLastMonthCommand.class);

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramLimit = request.getParameter(PAGE_SIZE);

        List<ReportBestProductByCountReceipt> bestProductsByCountReceipt =
                productService.getBestProductsByCountReceiptForTheLastMonth(Integer.parseInt(paramLimit));

        Set<Integer> ids = new HashSet<>();
        for (ReportBestProductByCountReceipt products : bestProductsByCountReceipt) {
            ids.add(products.getProductId());
        }

        List<Product> products = productService.getProductsByIds(ids);

        request.setAttribute(PRODUCTS, products);
        request.setAttribute(REPORT_BEST_PRODUCTS_BY_COUNT_RECEIPT, bestProductsByCountReceipt);

        return Page.BEST_PRODUCTS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH;
    }
}
