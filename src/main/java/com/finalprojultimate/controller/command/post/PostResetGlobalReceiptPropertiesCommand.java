package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.finalprojultimate.util.Command.REDIRECTED;
import static com.finalprojultimate.util.Command.CONTROLLER;
import static com.finalprojultimate.util.Command.SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES;
import static com.finalprojultimate.util.Path.*;

public class PostResetGlobalReceiptPropertiesCommand implements Command {
    private static final Logger logger = Logger.getLogger(PostResetGlobalReceiptPropertiesCommand.class);
    private static final String RESET_GLOBAL_RECEIPT_PROPERTIES = "Global receipt properties reset successfully!";

    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        receiptService.resetGlobalReceiptProperties();
        logger.info(RESET_GLOBAL_RECEIPT_PROPERTIES);
        response.sendRedirect(CONTROLLER + QUESTION_MARK + COMMAND + EQUALS_MARK + SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES);
        return REDIRECTED;
    }
}
