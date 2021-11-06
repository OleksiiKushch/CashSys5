package com.finalprojultimate.db.dao.entitydao;

import com.finalprojultimate.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.Product;
import com.finalprojultimate.model.entity.User;

import java.util.List;

public interface UserDAO extends EntityDAO<User> {
    User getUserByEmail(String email) throws DaoException;
}
