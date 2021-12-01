package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.UserService;
import com.finalprojultimate.model.services.impl.ProductServiceImpl;
import com.finalprojultimate.model.services.impl.UserServiceImpl;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class GetBestCashierForTheLastMonthCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetBestCashierForTheLastMonthCommand.class);

    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramLimit = request.getParameter(Attribute.PAGE_SIZE);

        LinkedHashMap<Integer, Integer> bestCashiersByCountReceipt =
                userService.getBestCashiersByCountReceipt(Integer.parseInt(paramLimit));

        Set<Integer> ids = bestCashiersByCountReceipt.keySet();

        List<User> cashiers = userService.getUsersByIds(ids);
        List<Integer> amountReceipts = new ArrayList<>(bestCashiersByCountReceipt.values());

        request.setAttribute(Attribute.USERS, cashiers);
        request.setAttribute(Attribute.AMOUNT, amountReceipts);

        return Page.BEST_CASHIER_FOR_THE_LAST_MONTH;
    }
}
