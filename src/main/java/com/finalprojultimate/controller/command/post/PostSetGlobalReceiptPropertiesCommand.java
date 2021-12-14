package com.finalprojultimate.controller.command.post;

import com.finalprojultimate.controller.command.AbstractCommandWrapper;
import com.finalprojultimate.controller.validation.impl.ReceiptPropertiesValidator;
import com.finalprojultimate.controller.validation.Validator;
import com.finalprojultimate.model.entity.receipt.ReceiptDetails;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.impl.ReceiptServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static com.finalprojultimate.util.Attribute.ERROR_MESSAGE;
import static com.finalprojultimate.util.Attribute.ERROR_VALIDATION_MESSAGE;
import static com.finalprojultimate.util.Page.SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;
import static com.finalprojultimate.util.Parameter.*;
import static com.finalprojultimate.util.Path.*;

public class PostSetGlobalReceiptPropertiesCommand extends AbstractCommandWrapper<ReceiptDetails> {
    private static final Logger logger = Logger.getLogger(PostSetGlobalReceiptPropertiesCommand.class);
    private static final String SET_GLOBAL_RECEIPT_PROPERTIES = "Global receipt properties set successfully!";

    private final ReceiptService receiptService = ReceiptServiceImpl.getInstance();

    private final Validator<ReceiptDetails> validator = new ReceiptPropertiesValidator();

    public PostSetGlobalReceiptPropertiesCommand() {
        super(SET_GLOBAL_RECEIPT_PROPERTIES_PAGE);
    }

    @Override
    protected String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ReceiptDetails globalReceiptProperties = getDataFromRequest(request);

        // writeSpecificDataToRequest(request, user);

        if(!validator.isValid(globalReceiptProperties)){
            extractAndWriteErrorMessagesToRequest(request);
            return SET_GLOBAL_RECEIPT_PROPERTIES_PAGE;
        }

        receiptService.setGlobalReceiptProperties(globalReceiptProperties);

        logger.info(SET_GLOBAL_RECEIPT_PROPERTIES);
        response.sendRedirect(CONTROLLER + "?command=" + SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES);
        return REDIRECTED;
    }

    @Override
    protected ReceiptDetails getDataFromRequest(HttpServletRequest request) {
        String organizationTaxIdNumber = request.getParameter(ORGANIZATION_TAX_ID_NUMBER);
        String nameOrganization = request.getParameter(NAME_ORGANIZATION);
        String addressTradePoint = request.getParameter(ADDRESS_TRADE_POINT);
        String vat = request.getParameter(VAT);
        String taxationSys = request.getParameter(TAXATION_SYS);

        return new ReceiptDetails.Builder()
                .withOrganizationTaxIdNumber(Long.parseLong(organizationTaxIdNumber))
                .withNameOrganization(nameOrganization)
                .withAddressTradePoint(addressTradePoint)
                .withVat(new BigDecimal(vat))
                .withTaxationSys(taxationSys)
                .build();
    }

    @Override
    protected void writeSpecificDataToRequest(HttpServletRequest request, ReceiptDetails data) {}

    private void extractAndWriteErrorMessagesToRequest(HttpServletRequest request) {
        List<String> errorMessages = validator.getErrorMessages();
        List<String> errorValidationMessages =
                validator.getErrorValidationMessages();
        request.setAttribute(ERROR_MESSAGE, errorMessages);
        request.setAttribute(ERROR_VALIDATION_MESSAGE, errorValidationMessages);
    }
}
