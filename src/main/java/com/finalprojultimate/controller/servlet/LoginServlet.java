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
        // log
        DAOFactory daoFactory = new MySqlDAOFactory();
        userDAO = daoFactory.getUserDAO();
        userDAO.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = User.hashPassword(request.getParameter("password"));

        if (true) {
            // TODO check email syntax
        }

        User user = null;
        try {
            user = userDAO.getUserByEmail(email);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }

        if (true /* user == null */ ) {
            // TODO check email in DB
        }

        String passHash = User.hashPassword(password);



    }
}
