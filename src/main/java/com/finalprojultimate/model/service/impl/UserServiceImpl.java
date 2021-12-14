package com.finalprojultimate.model.service.impl;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.util.DAOConstants;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.security.BCryptEncryptor;
import com.finalprojultimate.model.service.UserService;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.service.util.LoginData;
import com.finalprojultimate.util.MessageKey;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import static com.finalprojultimate.util.Parameter.EMAIL;

public class UserServiceImpl implements UserService {
    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    private static final String LOGGER_NO_SUCH_LOGIN_OR_PASSWORD =
            "Login failed : no such login or password in the database" +
                    " - LOGIN : %s";

    private final UserDAO userDAO;

    private static class Holder {
        static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public UserServiceImpl() {
        DAOFactory.setDaoFactoryFCN(DAOConstants.MY_SQL_DAO_FACTORY_FCN);
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        userDAO = daoFactory.getUserDAO();
        userDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    public User login(LoginData loginData) {
        User result = userDAO.getUserByEmail(loginData.getEmail());
        BCryptEncryptor encryptor = new BCryptEncryptor();
        if (!encryptor.checkPassword(loginData.getPassword(), result.getPassHash())) {
            throw new ServiceException()
                    .addMessage(MessageKey.ERROR_INCORRECT_LOGIN_DATA)
                    .addLogMessage(String.format(LOGGER_NO_SUCH_LOGIN_OR_PASSWORD,loginData.getEmail()))
                    .setClassThrowsException(UserServiceImpl.class);
        }
        return result;
    }

    @Override
    public void create(User user) {
        user.setPassHash(encryptUserPassword(user.getPassHash()));
        userDAO.insert(user);
    }

    @Override
    public String getFormattedNameById(int id) {
        return userDAO.getById(id).getFormattedName();
    }

    /**
     * return users (cashiers) from the database, sorted by their count of receipt, respectively
     *
     * @param limit number of return users (cashiers) (size LinkedHashMap)
     * @return LinkedHashMap (id user, count receipt)
     */
    @Override
    public LinkedHashMap<Integer, Integer> getBestCashiersByCountReceiptForTheLastMonth(int limit) {
        return userDAO.findBestCashiersByCountReceiptForTheLastMonth(limit);
    }

    @Override
    public List<User> getUsersByIds(Set<Integer> ids) {
        return userDAO.findUsersByIds(ids);
    }

    @Override
    public List<User> getForPagination(int offset, int limit) {
        return userDAO.findUsersWithPaginationSortByNone(offset, limit);
    }

    /**
     * return users from the database in the range [(offset - 1) * limit; offset * limit] pre-sorted by parameter or not (none)
     *
     * @param sortParameter parameter sorting [none, email]
     * @param offset number of page at pagination
     * @param limit number of return users (size List)
     * @return list of users
     */
    @Override
    public List<User> getForPaginationSortByParameter(String sortParameter, int offset, int limit) {
        if (sortParameter.equals(EMAIL)) {
            return userDAO.findUsersWithPaginationSortByEmail(offset, limit);
        } else {
            return getForPagination(offset, limit);
        }
    }

    @Override
    public int getCount() {
        return userDAO.getCountOfUsers();
    }

    private String encryptUserPassword(String password) {
        BCryptEncryptor encryptor = new BCryptEncryptor();
        return encryptor.encryptPassword(password);
    }
}
