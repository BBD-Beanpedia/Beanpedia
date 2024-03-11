package net.ryan.bean;

import net.ryan.cli.Nameable;

public record BeanTypeModel(int id, String type, String description) implements Nameable {
    @Override
    public String getName() {
        return type;
    }
}
