package net.ryan.bean;

import net.ryan.cli.Identifiable;
import net.ryan.cli.Nameable;

public record BeanColourModel(int id, String colour, String description) implements Nameable, Identifiable {
    @Override
    public String getName() {
        return colour;
    }

    @Override
    public int getId() {
        return id;
    }
}
