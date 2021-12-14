package com.finalprojultimate.model.db.dao.mysql;

import com.finalprojultimate.model.db.DBInit;
import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.DirectConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.user.Role;
import com.finalprojultimate.model.entity.user.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MySqlUserDAOTest {

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
    public void saveAndDeleteTest() throws DaoException {
        User user = new User.Builder()
                .withEmail("ben.cupper@.cashsys.com")
                .withFirstName("Ben")
                .withMiddleName("Dilan")
                .withLastName("Cupper")
                .withPassHash("82A280E5FF6A23229A5FCE280C0D14C84832FE019D0AD5CDA140C1BFD6B19112")
                .withRole(Role.CASHIER)
                .build();
        userDAO.insert(user);
        assertEquals("Ben", userDAO.getById(8).getFirstName());
        userDAO.delete(user);
        assertNull(userDAO.getById(8));
    }

    @Test
    public void update() throws DaoException {
        User uBefore = userDAO.getById(1);
        User uAfter = new User.Builder()
                .withId(uBefore.getId())
                .withEmail(uBefore.getEmail())
                .withFirstName(uBefore.getFirstName())
                .withMiddleName(uBefore.getMiddleName())
                .withLastName("Bobber")
                .withPassHash(uBefore.getPassHash())
                .withRole(uBefore.getRole())
                .build();
        userDAO.update(uAfter);
        assertEquals("Bobber", userDAO.getById(1).getLastName());
        userDAO.update(uBefore);
        assertEquals("Reuben", userDAO.getById(1).getLastName());
    }

    @Test
    public void getById() throws DaoException {
        assertEquals("Alex", userDAO.getById(3).getFirstName());
    }

    @Test
    public void getAll() throws DaoException {
        List<User> result = userDAO.getAll();
        assertEquals(7, result.size());
    }

    @Test
    public void getByEmail() throws DaoException {
        assertEquals("Ado", userDAO.getUserByEmail("tom.lee@cashsys.com").getMiddleName());
    }

    @Test
    public void findBestCashiersByCountReceiptForTheLastMonth() {
        int limit = 3;
        LinkedHashMap<Integer, Integer> result = userDAO.findBestCashiersByCountReceiptForTheLastMonth(limit);
        assertEquals("{1=4, 2=3, 4=1}", result.toString());
    }

    @Test
    public void findUsersByIds() {
        Set<Integer> test = new HashSet<>();
        test.add(1);
        test.add(2);
        List<User> result = userDAO.findUsersByIds(test);
        assertEquals("[User{id=1, email='tammy.reuben@cashsys.com', firstName='Tammy', middleName='Donald', lastName='Reuben', " +
                "passHash='79B180E5FF6A23229A5FCE280C0D14C84832FE019D0AD5CDA140C1BFD6B19112', role=Role{id=1, name='cashier'}}, " +
                "User{id=2, email='bob.yang@cashsys.com', firstName='Bob', middleName='Ken', lastName='Yang', " +
                "passHash='4E672DBA718E2552FC20E5ECFD4A0EFB47609F50EC10CB21DCA75C0800D124D5', role=Role{id=1, name='cashier'}}]",
                result.toString());
    }

    @Test
    public void getCountOfUsers() {
        int result = userDAO.getCountOfUsers();
        assertEquals(7, result);
    }

    @Test
    public void findProductsWithPaginationSortByNone() {
        List<User> result = userDAO.findUsersWithPaginationSortByNone(2, 2);
        assertEquals(
                "[User{id=3, email='alex.fpster@cashsys.com', firstName='Alex', middleName='Henry', lastName='Fpster', " +
                        "passHash='DB0BDF2FC0893C0848CC11795E952735A2E18D38F008E39C568961B477BD2CB7', role=Role{id=1, name='cashier'}}, " +
                        "User{id=4, email='maria.miller@cashsys.com', firstName='Maria', middleName='Loiuse', lastName='Miller', " +
                        "passHash='3F94E34DD40435CCF08E34AA158F6630ED7F11017C781F83A9BB5FF2129BBDE7', role=Role{id=1, name='cashier'}}]",
                result.toString());
    }

    @Test
    public void findProductsWithPaginationSortByEmail() {
        List<User> result = userDAO.findUsersWithPaginationSortByEmail(2, 2);
        assertEquals(
                "[User{id=5, email='jennifer.white@cashsys.com', firstName='Jennifer', middleName='Mie', lastName='White', " +
                        "passHash='AB72FD85278EBD7EAB739ABD3A9BCD02A901B186AAD57EDB82CBA3E6BE98DCC4', role=Role{id=1, name='cashier'}}, " +
                        "User{id=7, email='jeri.wood@cashsys.com', firstName='Jeri', middleName='T', lastName='Wood', " +
                        "passHash='34EBA500E76F442BC2DB6A5C04CDD2979D11D5848BF982D8E3DFF25EF2777A26', role=Role{id=3, name='commodity expert'}}]",
                result.toString());
    }
}