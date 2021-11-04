package com.finalprojultimate.db.dao;

import com.finalprojultimate.db.dao.entitydao.UserDAO;
import com.finalprojultimate.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.db.dao.entitydao.ReceiptDAO;

import java.lang.reflect.InvocationTargetException;

public abstract class DAOFactory {

    private static DAOFactory instance;

    public static synchronized DAOFactory getInstance() throws NoSuchMethodException, ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        if (instance == null) {
            Class<?> clazz = Class.forName(DAOFactory.daoFactoryFCN);
            instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    protected DAOFactory() {
        // nothing to do
    }

    private static String daoFactoryFCN;

    public static void setDaoFactoryFCN(String daoFactoryFCN) {
        instance = null;
        DAOFactory.daoFactoryFCN = daoFactoryFCN;
    }

    public abstract ProductDAO getProductDAO();

    public abstract UserDAO getUserDAO();

    public abstract ReceiptDAO getReceiptDAO();

}
