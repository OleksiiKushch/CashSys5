package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.ProductService;
import com.finalprojultimate.model.services.UserService;
import com.finalprojultimate.model.services.util.LoginData;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService testUserService;

    @BeforeClass
    public static void startUp() throws Exception {
        DBInit.startUp();
    }

    @Before
    public void setUp() throws Exception {
        Class<?> clazz = UserServiceImpl.class;
        Object initUserServiceImpl = clazz.newInstance();

        Field f1 = initUserServiceImpl.getClass().getDeclaredField("userDAO");
        f1.setAccessible(true);

        DAOFactory daoFactory = new MySqlDAOFactory();
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.setConnectionBuilder(new DirectConnectionBuilder());

        f1.set(initUserServiceImpl, userDAO);

        testUserService = (UserServiceImpl) initUserServiceImpl;
    }

    @Test
    public void userServiceInitTest() {
        UserService userService = UserServiceImpl.getInstance();
        assertNotNull(userService);
    }

    @Test
    public void createAndLoginTest() throws SQLException, URISyntaxException, IOException {
        User user = new User.Builder()
                .withEmail("alex.sunderhaft@cashsys.com")
                .withFirstName("Alex")
                .withMiddleName("Dilan")
                .withLastName("Sunderhaft")
                .withPassHash("1234") // temporarily password
                .withRole(Role.CASHIER)
                .build();

        testUserService.create(user);

        LoginData loginData = new LoginData("alex.sunderhaft@cashsys.com",
                "1234");

        User result = testUserService.login(loginData);

        assertEquals("alex.sunderhaft@cashsys.com", result.getEmail());

        DBInit.startUp();
    }

    @Test
    public void getFormattedNameByIdTest() {
        String result = testUserService.getFormattedNameById(4);
        assertEquals("Miller M.L.", result);
    }

    @Test
    public void getBestCashiersByCountReceiptTest() {
        LinkedHashMap<Integer, Integer> result = testUserService.getBestCashiersByCountReceipt(2);
        assertEquals("{1=4, 2=3}", result.toString());
    }

    @Test
    public void getUsersByIdsTest() {
        Set<Integer> ids = new HashSet<>();
        ids.add(1);
        ids.add(6);
        List<User> result = testUserService.getUsersByIds(ids);
        assertEquals(
                "[User{id=1, email='tammy.reuben@cashsys.com', firstName='Tammy', middleName='Donald', lastName='Reuben', " +
                "passHash='79B180E5FF6A23229A5FCE280C0D14C84832FE019D0AD5CDA140C1BFD6B19112', role=Role{id=1, name='cashier'}}, " +
                "User{id=6, email='tom.lee@cashsys.com', firstName='Tom', middleName='Ado', lastName='Lee', " +
                "passHash='DA0AED7FEDE29351692B86BADC77859B4716C7195E37571D531B9108686917C4', role=Role{id=2, name='senior cashier'}}]",
                result.toString());
    }

}