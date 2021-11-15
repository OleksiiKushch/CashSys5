package com.finalprojultimate.controller.command;

import com.finalprojultimate.controller.command.get.*;
import com.finalprojultimate.controller.command.login.*;
import com.finalprojultimate.controller.command.post.PostCreateNewProductCommand;
import com.finalprojultimate.controller.command.post.PostDeleteProductCommand;
import com.finalprojultimate.controller.command.post.PostEditProductCommand;
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

        commands.put(POST + LOGIN, new LoginSubmitCommand());
        commands.put(POST + REGISTRATION, new RegistrationSubmitCommand());
        commands.put(POST + CREATE_NEW_PRODUCT, new PostCreateNewProductCommand());
        commands.put(POST + EDIT_PRODUCT, new PostEditProductCommand());
        commands.put(POST + DELETE_PRODUCT, new PostDeleteProductCommand());
    }

    public Command getCommandByKey(String commandKey) {
        return commands.getOrDefault(commandKey, unsupportedPathCommand);
    }
}
