package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.UserDAO;
import com.finalprojultimate.model.db.dao.util.DAOConstants;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.security.BCryptEncryptor;
import com.finalprojultimate.model.services.UserService;
import com.finalprojultimate.model.services.exception.ServiceException;
import com.finalprojultimate.model.services.util.LoginData;
import com.finalprojultimate.util.MessageKey;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

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
        DAOFactory.setDaoFactoryFCN(DAOConstants.MySqlDAOFactoryFCN);
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        assert daoFactory != null;
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
        userDAO.insert(user);
    }

    @Override
    public String getFormattedNameById(int id) {
        return userDAO.getById(id).getFormattedName();
    }
}
