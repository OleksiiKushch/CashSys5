package com.finalprojultimate.model.db.dao.connection;

import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class ConnectionTest {

    ProductDAO productDAO;

    @Before
    public void setUp() {

    }

    @Test
    public void poolConnectionBuilderTest() throws SQLException {
        PoolConnectionBuilder poolConnectionBuilder = Mockito.mock(PoolConnectionBuilder.class);
        PoolConnectionBuilder.getInstance();
        assertTrue(poolConnectionBuilder.getConnection().isValid(1));

//        DAOFactory daoFactory = new MySqlDAOFactory();
//        productDAO = daoFactory.getProductDAO();
//        productDAO.setConnectionBuilder(new DirectConnectionBuilder());

    }
}
