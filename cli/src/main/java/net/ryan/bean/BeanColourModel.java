package net.ryan.bean;

import net.ryan.cli.Nameable;

public record BeanColourModel(int id, String colour) implements Nameable {
    @Override
    public String getName() {
        return colour;
    }
}
