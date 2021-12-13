package com.finalprojultimate.model.entity.receipt;

import java.util.Objects;

public enum Status {
    NORMAL (1, "normal", "normal"),
    REJECTED (2, "rejected", "rejected");

    private final int id;
    private final String name;
    private final String message;

    Status(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    /**
     * return null if element by id not found
     */
    public static Status getById(int id) {
        for (Status status : Status.values()) {
            if (status.getId() == id)
                return status;
        }
        return null;
    }

    /**
     * return null if element by name not found
     */
    public static Status getByName(String name) {
        for (Status status : Status.values()) {
            if (Objects.equals(status.getName(), name))
                return status;
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
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
