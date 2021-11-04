package com.finalprojultimate;

import com.finalprojultimate.db.dao.DAOFactory;
import com.finalprojultimate.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.dao.mysql.MySqlDAOFactory;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet("/test")
public class HelloServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        // log
        DAOFactory daoFactory = new MySqlDAOFactory();
        productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new PoolConnectionBuilder());
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Start!");
        response.getWriter().print("Hi~!");

    }

}