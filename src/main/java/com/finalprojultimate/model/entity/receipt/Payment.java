package com.finalprojultimate.model.entity.receipt;

import java.util.Objects;

public enum Payment {
    CASH (1, "cash", "cash"),
    ELECTRONIC (2, "electronic", "electronic");

    private final int id;
    private final String name;
    private final String message;

    Payment(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    /**
     * return null if element by id not found
     */
    public static Payment getById(int id) {
        for (Payment payment : Payment.values()) {
            if (payment.getId() == id)
                return payment;
        }
        return null;
    }

    /**
     * return null if element by id not found
     */
    public static Payment getByName(String name) {
        for (Payment payment : Payment.values()) {
            if (Objects.equals(payment.getName(), name))
                return payment;
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
        return "Payment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
