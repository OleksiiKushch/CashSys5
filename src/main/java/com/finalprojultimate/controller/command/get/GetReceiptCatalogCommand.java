package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetReceiptCatalogCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetReceiptCatalogCommand.class);

    private static final int SHIFT = 0;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramPage = request.getParameter(Attribute.PAGE);
        String paramPageSize = request.getParameter(Attribute.PAGE_SIZE);

        int page = Integer.parseInt(paramPage);
        int pageSize = Integer.parseInt(paramPageSize);

        String sortParam = request.getParameter(Attribute.RECEIPT_SORT_PARAM);
        HttpSession session = request.getSession();
        if (sortParam == null) {
            sortParam = (String) session.getAttribute(Attribute.RECEIPT_SORT_PARAM);
        } else {
            session.setAttribute(Attribute.RECEIPT_SORT_PARAM, sortParam);
        }

        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        List<Receipt> paginateReceipts;
        if (sortParam == null) {
            paginateReceipts = receiptService.getForPagination(pageSize * (page - 1), pageSize);
        } else {
            paginateReceipts = receiptService.getForPaginationSortByParameter(sortParam, pageSize * (page - 1), pageSize);
        }

        int size = receiptService.getCount();

        int minPagePossible = Math.max(page - SHIFT, 1);
        int pageCount = (int) Math.ceil((double) size / (double) pageSize);
        int maxPagePossible = Math.min(page + SHIFT, pageCount);

        request.setAttribute(Attribute.PAGINATE_RECEIPTS, paginateReceipts);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);
        request.setAttribute(Attribute.PAGE, page);
        request.setAttribute(Attribute.PAGE_SIZE, pageSize);
        request.setAttribute(Attribute.MIN_POSSIBLE_PAGE, minPagePossible);
        request.setAttribute(Attribute.MAX_POSSIBLE_PAGE, maxPagePossible);

        return Page.RECEIPT_CATALOG_PAGE;
    }
}
