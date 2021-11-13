package com.finalprojultimate.model.security;

public interface Securable {
    String encryptPassword(String password);
    boolean checkPassword(String password, String hash);
}
