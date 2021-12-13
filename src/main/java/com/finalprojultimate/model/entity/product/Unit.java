package com.finalprojultimate.model.entity.product;

import java.util.Objects;

public enum Unit {
    PIECES (1, "pieces", "pieces"),
    KILOGRAM (2, "kilogram", "kilogram"),
    LITRE (3, "litre", "litre");

    private final int id;
    private final String name;
    private final String message;

    Unit(int id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    /**
     * return null if element by id not found
     */
    public static Unit getById(int id) {
        for (Unit rootCategory : Unit.values()) {
            if (rootCategory.getId() == id)
                return rootCategory;
        }
        return null;
    }

    /**
     * return null if element by name not found
     */
    public static Unit getByName(String name) {
        for (Unit unit : Unit.values()) {
            if (Objects.equals(unit.getName(), name))
                return unit;
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
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
