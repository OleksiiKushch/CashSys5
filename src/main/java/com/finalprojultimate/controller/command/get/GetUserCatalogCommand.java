package com.finalprojultimate.controller.command.get;

import com.finalprojultimate.controller.command.Command;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.impl.UserServiceImpl;
import com.finalprojultimate.util.Attribute;
import com.finalprojultimate.util.Page;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GetUserCatalogCommand implements Command {
    private static final Logger logger = Logger.getLogger(GetUserCatalogCommand.class);

    private static final int SHIFT = 0;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paramPage = request.getParameter(Attribute.PAGE);
        String paramPageSize = request.getParameter(Attribute.PAGE_SIZE);

        int page = Integer.parseInt(paramPage);
        int pageSize = Integer.parseInt(paramPageSize);

        String sortParam = request.getParameter(Attribute.USER_SORT_PARAM);
        HttpSession session = request.getSession();
        if (sortParam == null) {
            sortParam = (String) session.getAttribute(Attribute.USER_SORT_PARAM);
        } else {
            session.setAttribute(Attribute.USER_SORT_PARAM, sortParam);
        }

        UserService userService = UserServiceImpl.getInstance();
        List<User> paginateUsers;
        if (sortParam == null) {
            paginateUsers = userService.getForPagination(pageSize * (page - 1), pageSize);
        } else {
            paginateUsers = userService.getForPaginationSortByParameter(sortParam, pageSize * (page - 1), pageSize);
        }

        int size = userService.getCount();

        int minPagePossible = Math.max(page - SHIFT, 1);
        int pageCount = (int) Math.ceil((double) size / (double) pageSize);
        int maxPagePossible = Math.min(page + SHIFT, pageCount);

        request.setAttribute(Attribute.PAGINATE_USERS, paginateUsers);
        request.setAttribute(Attribute.PAGE_COUNT, pageCount);
        request.setAttribute(Attribute.PAGE, page);
        request.setAttribute(Attribute.PAGE_SIZE, pageSize);
        request.setAttribute(Attribute.MIN_POSSIBLE_PAGE, minPagePossible);
        request.setAttribute(Attribute.MAX_POSSIBLE_PAGE, maxPagePossible);

        return Page.USER_CATALOG_PAGE;
    }
}
