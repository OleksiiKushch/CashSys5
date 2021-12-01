package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.util.LoginData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface UserService {
    User login(LoginData loginData);
    void create(User user); // registration
    String getFormattedNameById(int id);
    LinkedHashMap<Integer, Integer> getBestCashiersByCountReceipt(int limit);
    List<User> getUsersByIds(Set<Integer> ids);
}
