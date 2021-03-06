package com.finalprojultimate.controller.command.get.report;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.finalprojultimate.util.Attribute.*;

public class GetBestCashiersByCountReceiptsForTheLastMonthCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetBestCashiersByCountReceiptsForTheLastMonthCommand.class);
    private static final String GENERATE_REPORT_BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH =
            "Generate report (best cashiers by count receipts for the last month) successfully!";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramLimit = request.getParameter(PAGE_SIZE);

        UserService userService = UserServiceImpl.getInstance();

        LinkedHashMap<Integer, Integer> bestCashiersByCountReceipt =
                userService.getBestCashiersByCountReceiptForTheLastMonth(Integer.parseInt(paramLimit));

        Set<Integer> ids = bestCashiersByCountReceipt.keySet();

        List<User> cashiers = userService.getUsersByIds(ids);
        List<Integer> amountReceipts = new ArrayList<>(bestCashiersByCountReceipt.values());

        request.setAttribute(USERS, cashiers);
        request.setAttribute(AMOUNT, amountReceipts);

        logger.info(GENERATE_REPORT_BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH);
        return Page.BEST_CASHIERS_BY_COUNT_RECEIPTS_FOR_THE_LAST_MONTH;
    }
}
