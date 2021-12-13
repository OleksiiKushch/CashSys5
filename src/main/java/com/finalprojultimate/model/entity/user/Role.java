package com.finalprojultimate.model.entity.user;

import java.util.Objects;

public enum Role {
    CASHIER (1, "cashier", "cashier"),
    SENIOR_CASHIER (2, "senior cashier", "senior.cashier"),
    COMMODITY_EXPERT (3, "commodity expert", "commodity.expert");

    private final int id;
    private final String name;
    private final String message;

    Role(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    /**
     * return null if element by id not found
     */
    public static Role getById(int id) {
        for (Role role : Role.values()) {
            if (role.getId() == id)
                return role;
        }
        return null;
    }

    /**
     * return null if element by name not found
     */
    public static Role getByName(String name) {
        for (Role role : Role.values()) {
            if (Objects.equals(role.getName(), name))
                return role;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
