package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.impl.ProductServiceImpl;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.finalprojultimate.util.Parameter.BY_BARCODE;
import static com.finalprojultimate.util.Parameter.BY_NAME;

public class GetFindProductsByParameterCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetFindProductsByParameterCommand.class);

    private final ProductService productService = ProductServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameterSearching = request.getParameter(Attribute.PARAMETER_SEARCHING);
        String patternSearching = request.getParameter(Attribute.PATTERN_SEARCHING);

        // TODO validate parameter searching is not null

        List<Product> products = null;

        if (parameterSearching.equals(BY_BARCODE)) {
            products = productService.findProductsByBarcode(patternSearching);
        } else if (parameterSearching.equals(BY_NAME)) {
            products = productService.findProductsByName(patternSearching);
        }

        request.setAttribute(Attribute.PRODUCTS_FOUND, products);

        return Page.CREATE_NEW_RECEIPT_PAGE;
    }
}
