package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.*;

public class MySqlUserDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(MySqlUserDAOTest.class);

    UserDAO userDAO;

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Before
    public void setUp() {
        DAOFactory daoFactory = new MySqlDAOFactory();
        userDAO = daoFactory.getUserDAO();
        userDAO.setConnectionBuilder(new DirectConnectionBuilder());
    }

    @Test
    public void saveDeleteTest() throws DaoException {
        User user = new User.Builder()
                .withEmail("ben.cupper@.cashsys.com")
                .withFirstName("Ben")
                .withMiddleName("Dilan")
                .withLastName("Cupper")
                .withPassHash("82A280E5FF6A23229A5FCE280C0D14C84832FE019D0AD5CDA140C1BFD6B19112")
                .withRoleId(User.Role.CASHIER)
                .build();
        userDAO.insert(user);
        assertEquals("Ben", userDAO.getById(8).getFirstName());
        userDAO.delete(user);
        assertNull(userDAO.getById(8));
    }

    @Test
    public void updateTest() throws DaoException {
        User uBefore = userDAO.getById(1);
        User uAfter = new User.Builder()
                .withId(uBefore.getId())
                .withEmail(uBefore.getEmail())
                .withFirstName(uBefore.getFirstName())
                .withMiddleName(uBefore.getMiddleName())
                .withLastName("Bobber")
                .withPassHash(uBefore.getPassHash())
                .withRoleId(uBefore.getRole())
                .build();
        userDAO.update(uAfter);
        assertEquals("Bobber", userDAO.getById(1).getLastName());
        userDAO.update(uBefore);
        assertEquals("Reuben", userDAO.getById(1).getLastName());
    }

    @Test
    public void getByIdTest() throws DaoException {
        assertEquals("Alex", userDAO.getById(3).getFirstName());
    }

    @Test
    public void getAllTest() throws DaoException {
        List<User> result = userDAO.getAll();
        assertEquals(7, result.size());
    }

    @Test
    public void getByEmailTest() throws DaoException {
        assertEquals("Ado", userDAO.getUserByEmail("tom.lee@cashsys.com").getMiddleName());
    }
}