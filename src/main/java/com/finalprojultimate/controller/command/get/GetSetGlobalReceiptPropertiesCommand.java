package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Attribute.GLOBAL_RECEIPT_PROPERTIES;
import static com.finalprojultimate.util.Page.SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;

public class GetSetGlobalReceiptPropertiesCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetSetGlobalReceiptPropertiesCommand.class);

    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReceiptDetails receiptDetails = receiptService.getGlobalReceiptProperties();
        request.setAttribute(GLOBAL_RECEIPT_PROPERTIES, receiptDetails);
        return SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;
    }
}
