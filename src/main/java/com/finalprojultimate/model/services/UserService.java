package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.user.User;
import com.finalprojultimate.model.services.util.LoginData;

public interface UserService {
    User login(LoginData loginData);
    void registration(User user);
}
