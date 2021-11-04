package com.finalprojultimate.db.dao.mysql;

import com.finalprojultimate.db.dao.entitydao.UserDAO;
import com.finalprojultimate.db.dao.DAOFactory;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.dao.entitydao.ReceiptDAO;

public class MySqlDAOFactory extends DAOFactory {

    private ProductDAO productDAO;
    private UserDAO userDAO;
    private ReceiptDAO receiptDAO;

    @Override
    public synchronized ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new MySqlProductDAO();
        }
        return productDAO;
    }

    @Override
    public synchronized UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new MySqlUserDAO();
        }
        return userDAO;
    }

    @Override
    public synchronized ReceiptDAO getReceiptDAO() {
        if (receiptDAO == null) {
            receiptDAO = new MySqlReceiptDAO();
        }
        return receiptDAO;
    }

}
