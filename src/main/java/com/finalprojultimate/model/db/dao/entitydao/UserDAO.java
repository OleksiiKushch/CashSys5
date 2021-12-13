package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.user.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface UserDAO extends EntityDAO<User> {
    User getUserByEmail(String email) throws DaoException;
    LinkedHashMap<Integer, Integer> findBestCashiersByCountReceiptForTheLastMonth(int limit) throws DaoException;
    List<User> findUsersByIds(Set<Integer> ids);
    List<User> findUsersWithPaginationSortByNone(int offset, int limit) throws DaoException;
    List<User> findUsersWithPaginationSortByEmail(int offset, int limit) throws DaoException;
    int getCountOfUsers() throws DaoException;
}
