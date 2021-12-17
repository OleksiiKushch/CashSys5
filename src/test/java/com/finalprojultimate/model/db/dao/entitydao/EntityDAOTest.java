package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import org.junit.Test;

public class EntityDAOTest {

    @Test(expected = DaoException.class)
    public void generateException() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        ProductDAO productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(new DirectConnectionBuilder());

        throw productDAO.generateException("An error has occurred!", "an.error.has.occurred", RuntimeException.class);
    }
}