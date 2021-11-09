package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.User;

public interface UserDAO extends EntityDAO<User> {
    User getUserByEmail(String email) throws DaoException;
}
