package com.finalprojultimate.controller.servlet;

import com.finalprojultimate.db.dao.DAOFactory;
import com.finalprojultimate.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.UserDAO;
import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet", value = "/registration")
public class RegistrationServlet extends HttpServlet {
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/registration.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String password = User.hashPassword(request.getParameter("password"));
        String confirmationPassword = User.hashPassword(request.getParameter("confirmationPassword"));
        String role = request.getParameter("role");

        if (true) {
            // TODO check email syntax
        }

        if (true) {
            // TODO check email duplicate
        }

        if (true) {
            // TODO check password on confirmation
        }

        String passHash = User.hashPassword(password);

        User user = new User.Builder()
                .withEmail(email)
                .withFirstName(firstName)
                .withMiddleName(middleName)
                .withLastName(lastName)
                .withPassHash(passHash)
                .withRoleId(User.Role.getByName(role))
                .build();

        try {
            userDAO.insert(user);
        } catch (DaoException e) {
            logger.error(e.getMessage(), e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/registration_successful.jsp");
        dispatcher.forward(request, response);
    }
}
