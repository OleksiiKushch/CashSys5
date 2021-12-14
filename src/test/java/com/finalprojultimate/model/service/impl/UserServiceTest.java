package com.finalprojultimate.model.service.impl;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.mysql.MySqlDAOFactory;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.service.util.LoginData;
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
    public void userServiceInit() {
        UserService userService = UserServiceImpl.getInstance();
        assertNotNull(userService);
    }

    @Test(expected = ServiceException.class)
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

        loginData.setPassword("1235");
        testUserService.login(loginData);

        DBInit.startUp();
    }

    @Test
    public void getFormattedNameById() {
        String result = testUserService.getFormattedNameById(4);
        assertEquals("Miller M.L.", result);
    }

    @Test
    public void getBestCashiersByCountReceiptForTheLastMonth() {
        LinkedHashMap<Integer, Integer> result = testUserService.getBestCashiersByCountReceiptForTheLastMonth(2);
        assertEquals("{1=4, 2=3}", result.toString());
    }

    @Test
    public void getUsersByIds() {
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

    @Test
    public void getForPagination() {
        List<User> result = testUserService.getForPagination(2, 2);
        assertEquals("[User{id=3, email='alex.fpster@cashsys.com', firstName='Alex', middleName='Henry', lastName='Fpster', " +
                "passHash='DB0BDF2FC0893C0848CC11795E952735A2E18D38F008E39C568961B477BD2CB7', role=Role{id=1, name='cashier'}}, " +
                "User{id=4, email='maria.miller@cashsys.com', firstName='Maria', middleName='Loiuse', lastName='Miller', " +
                "passHash='3F94E34DD40435CCF08E34AA158F6630ED7F11017C781F83A9BB5FF2129BBDE7', role=Role{id=1, name='cashier'}}]",
                result.toString());
    }

    @Test
    public void getForPaginationSortByParameter() {
        List<User> result = testUserService.getForPaginationSortByParameter("none",2, 2);
        assertEquals(
                "[User{id=3, email='alex.fpster@cashsys.com', firstName='Alex', middleName='Henry', lastName='Fpster', " +
                        "passHash='DB0BDF2FC0893C0848CC11795E952735A2E18D38F008E39C568961B477BD2CB7', role=Role{id=1, name='cashier'}}, " +
                        "User{id=4, email='maria.miller@cashsys.com', firstName='Maria', middleName='Loiuse', lastName='Miller', " +
                        "passHash='3F94E34DD40435CCF08E34AA158F6630ED7F11017C781F83A9BB5FF2129BBDE7', role=Role{id=1, name='cashier'}}]",
                result.toString());

        result = testUserService.getForPaginationSortByParameter("email",2, 2);
        assertEquals(
                "[User{id=2, email='bob.yang@cashsys.com', firstName='Bob', middleName='Ken', lastName='Yang', " +
                        "passHash='4E672DBA718E2552FC20E5ECFD4A0EFB47609F50EC10CB21DCA75C0800D124D5', role=Role{id=1, name='cashier'}}, " +
                        "User{id=5, email='jennifer.white@cashsys.com', firstName='Jennifer', middleName='Mie', lastName='White', " +
                        "passHash='AB72FD85278EBD7EAB739ABD3A9BCD02A901B186AAD57EDB82CBA3E6BE98DCC4', role=Role{id=1, name='cashier'}}]",
                result.toString());
    }

    @Test
    public void getCount() {
        int result = testUserService.getCount();
        assertEquals(8, result);
    }

}