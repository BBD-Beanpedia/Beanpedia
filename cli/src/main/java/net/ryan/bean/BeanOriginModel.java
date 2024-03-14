package net.ryan.bean;

import net.ryan.cli.Identifiable;
import net.ryan.cli.Nameable;

public record BeanOriginModel(int id, String origin) implements Nameable, Identifiable {
    @Override
    public String getName() {
        return this.origin;
    }

    @Override
    public int getId() {
        return id;
    }
}
