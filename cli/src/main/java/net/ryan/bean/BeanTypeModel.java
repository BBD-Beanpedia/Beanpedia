package net.ryan.bean;

import net.ryan.cli.Identifiable;
import net.ryan.cli.Nameable;

public record BeanTypeModel(int id, String type, String description) implements Nameable, Identifiable {
    @Override
    public String getName() {
        return type;
    }

    @Override
    public int getId() {
        return id;
    }
}
