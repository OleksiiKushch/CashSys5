package com.finalprojultimate.controller.servlet;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CatalogProductsServlet", value = "/productCatalog")
public class ProductCatalogServlet extends HttpServlet {

    private ProductDAO productDAO;

    @Override
    public void init() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAll();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/view/jsp/productCatalog.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
