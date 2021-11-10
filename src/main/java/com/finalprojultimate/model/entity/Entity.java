package com.finalprojultimate.model.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    private static final long serialVersionUID = -9193104711863203024L;

    protected int id;

    protected Entity() {
        // nothing to do
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
