package net.ryan.bean;

import net.ryan.cli.Nameable;

public record BeanShapeModel(int id, String shape) implements Nameable {
    @Override
    public String getName() {
        return null;
    }
}
