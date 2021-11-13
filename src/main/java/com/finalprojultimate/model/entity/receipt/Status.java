package com.finalprojultimate.model.entity.receipt;

import java.util.Objects;

public enum Status {
    NORMAL (1, "normal"),
    REJECTED (2, "rejected");

    private final int id;
    private final String name;

    Status(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
