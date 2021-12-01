package com.finalprojultimate.controller.command;

import com.finalprojultimate.controller.command.get.*;
import com.finalprojultimate.controller.command.login.*;
import com.finalprojultimate.controller.command.get.GetAddProductToCartCommand;
import com.finalprojultimate.controller.command.post.*;
import com.finalprojultimate.controller.command.registration.*;

import java.util.HashMap;
import java.util.Map;

import static com.finalprojultimate.util.Path.*;

public class CommandHolder {
    private static final String GET = "GET:";
    private static final String POST = "POST:";

    private final UnsupportedPathCommand unsupportedPathCommand = new UnsupportedPathCommand();
    private final Map<String, Command> commands;

    public CommandHolder() {
        commands = new HashMap<>();
        initCommands();
    }

    private void initCommands() {
        commands.put(GET + MAIN, new GetMainCommand());
        commands.put(GET + LOGIN, new LoginCommand());
        commands.put(GET + LOGOUT, new GetLogoutCommand());
        commands.put(GET + REGISTRATION, new RegistrationCommand());
        commands.put(GET + SUCCESSFUL_REGISTRATION, new SuccessfulRegistrationCommand());
        commands.put(GET + PRODUCT_CATALOG, new GetProductCatalogCommand());
        commands.put(GET + EDIT_PRODUCT, new GetEditProductCommand());
        commands.put(GET + SUCCESSFUL_UPDATE_PRODUCT, new GetSuccessfulUpdateProductCommand());
        commands.put(GET + SUCCESSFUL_DELETE_PRODUCT, new GetSuccessfulDeleteProduct());
        commands.put(GET + CREATE_NEW_PRODUCT, new GetCreateNewProductCommand());
        commands.put(GET + SUCCESSFUL_CREATE_NEW_PRODUCT, new GetSuccessfulCreateNewProductCommand());
        commands.put(GET + RECEIPT_CATALOG, new GetReceiptCatalogCommand());
        commands.put(GET + FIND_PRODUCTS_BY_PARAMETER, new GetFindProductsByParameterCommand());
        commands.put(GET + NEW_RECEIPT, new GetNewReceiptCommand());
        commands.put(GET + CREATE_NEW_RECEIPT, new GetCreateNewReceiptCommand());
        commands.put(GET + CART_OF_PRODUCTS, new GetCartOfProductsCommand());
        commands.put(GET + ADD_PRODUCT_TO_CART, new GetAddProductToCartCommand());
        commands.put(GET + EDIT_PRODUCT_AMOUNT_FROM_CART, new GetEditProductFromCartCommand());
        commands.put(GET + DELETE_PRODUCT_FROM_CART, new GetDeleteProductFromCartCommand());
        commands.put(GET + SUCCESSFUL_CREATE_NEW_RECEIPT, new GetSuccessfulCreateNewReceiptCommand());
        commands.put(GET + SET_GLOBAL_RECEIPT_PROPERTIES, new GetSetGlobalReceiptPropertiesCommand());
        commands.put(GET + SUCCESSFUL_SET_GLOBAL_RECEIPT_PROPERTIES, new GetSuccessfulSetGlobalReceiptPropertiesCommand());
        commands.put(GET + SUCCESSFUL_RESET_GLOBAL_RECEIPT_PROPERTIES, new GetSuccessfulResetGlobalReceiptPropertiesCommand());
        commands.put(GET + SEE_RECEIPT_DETAILS, new GetSeeReceiptDetailsCommand());
        commands.put(GET + SUCCESSFUL_CREATE_NEW_REJECT_RECEIPT, new GetSuccessfulCreateNewRejectReceiptCommand());
        commands.put(GET + GENERATE_REPORT, new GetGenerateReportCommand());
        commands.put(GET + BEST_CASHIER_FOR_THE_MONTH, new GetBestCashierForTheLastMonthCommand());

        commands.put(POST + LOGIN, new LoginSubmitCommand());
        commands.put(POST + REGISTRATION, new RegistrationSubmitCommand());
        commands.put(POST + CREATE_NEW_PRODUCT, new PostCreateNewProductCommand());
        commands.put(POST + EDIT_PRODUCT, new PostEditProductCommand());
        commands.put(POST + DELETE_PRODUCT, new PostDeleteProductCommand());
        commands.put(POST + CREATE_NEW_RECEIPT, new PostCreateNewReceiptCommand());
        commands.put(POST + SET_GLOBAL_RECEIPT_PROPERTIES, new PostSetGlobalReceiptPropertiesCommand());
        commands.put(POST + RESET_GLOBAL_RECEIPT_PROPERTIES, new PostResetGlobalReceiptPropertiesCommand());
        commands.put(POST + CREATE_REJECT_RECEIPT, new PostCreateRejectReceiptCommand());

    }

    public Command getCommandByKey(String commandKey) {
        return commands.getOrDefault(commandKey, unsupportedPathCommand);
    }
}
