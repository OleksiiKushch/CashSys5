package com.finalprojultimate.model.entity.receipt;

import com.finalprojultimate.model.entity.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Receipt extends Entity {
    private static final long serialVersionUID = 2350326613305315221L;

    private LocalDateTime dateTime;
    private BigDecimal change;
    private Payment payment;
    private Status status;
    private int userId;

    public Receipt() {
        // default constructor
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int user_id) {
        this.userId = user_id;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", change=" + change +
                ", payment=" + payment +
                ", status=" + status +
                ", userId=" + userId +
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

        public Builder withUserId(int userId) {
            newReceipt.userId = userId;
            return this;
        }

        public Receipt build() {
            return newReceipt;
        }
    }
}
