package com.finalprojultimate.controller.servlet;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(RegistrationServlet.class);

    private UserDAO userDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        userDAO = daoFactory.getUserDAO();
        userDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/jsp/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = User.hashPassword(request.getParameter("password"));

        if (!User.checkEmail(email)) {
            // TODO check email syntax
            /* E-mail is invalid */
        }

        User user = null;
        try {
            user = userDAO.getUserByEmail(email);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }

        if (user == null) {
            // TODO check email in DB
            /* user is not found */
        }

        String passHash = User.hashPassword(password);

        assert user != null;
        if (!user.getPassHash().equals(passHash)) {
            // TODO check password
            /* wrong password */
        }

        request.getSession().setAttribute("loggedUser", user);

    }
}
