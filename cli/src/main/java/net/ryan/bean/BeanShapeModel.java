package net.ryan.bean;

import net.ryan.cli.Identifiable;
import net.ryan.cli.Nameable;

public record BeanShapeModel(int id, String shape, String description) implements Nameable, Identifiable {
    @Override
    public String getName() {
        return shape;
    }

    @Override
    public int getId() {
        return this.id;
    }
}
