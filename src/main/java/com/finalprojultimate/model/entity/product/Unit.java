package com.finalprojultimate.model.entity.product;

import java.util.Objects;

public enum Unit {
    PIECES (1, "pieces"),
    KILOGRAM (2, "kilogram"),
    LITRE (3, "litre");

    private final int id;
    private final String name;

    Unit(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
