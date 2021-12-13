package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.util.LoginData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface UserService {
    User login(LoginData loginData);
    void create(User user); // registration
    String getFormattedNameById(int id);
    LinkedHashMap<Integer, Integer> getBestCashiersByCountReceiptForTheLastMonth(int limit);
    List<User> getUsersByIds(Set<Integer> ids);
    List<User> getForPagination(int offset, int limit);
    List<User> getForPaginationSortByParameter(String sortParameter, int offset, int limit);
    int getCount();
}
