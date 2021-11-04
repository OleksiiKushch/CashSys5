package com.finalprojultimate.db.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Receipt {
    private int id;
    private LocalDateTime dateTime;
    private BigDecimal change;
    private Payment payment;
    private Status status;
    private User user;

    public Receipt() {
        // default constructor
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", change=" + change +
                ", payment=" + payment +
                ", status=" + status +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private final Receipt newReceipt;

        public Builder() {
            newReceipt = new Receipt();
        }

        public Builder withId(int id) {
            newReceipt.id = id;
            return this;
        }

        public Builder withDateTime(LocalDateTime dateTime) {
            newReceipt.dateTime = dateTime;
            return this;
        }

        public Builder withChange(BigDecimal change) {
            newReceipt.change = change;
            return this;
        }

        public Builder withPayment(Payment payment) {
            newReceipt.payment = payment;
            return this;
        }

        public Builder withStatus(Status status) {
            newReceipt.status = status;
            return this;
        }

        public Builder withUser(User user) {
            newReceipt.user = user;
            return this;
        }

        public Receipt build() {
            return newReceipt;
        }
    }

    public enum Payment {
        CASH (1, "cash"),
        ELECTRONIC (2, "electronic");

        private final int id;
        private final String name;

        Payment(int id, String name) {
            this.id = id;
            this.name = name;
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

        @Override
        public String toString() {
            return "Payment{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

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
}
