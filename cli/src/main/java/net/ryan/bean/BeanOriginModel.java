package net.ryan.bean;

import net.ryan.cli.Nameable;

public record BeanOriginModel(int id, String origin) implements Nameable {
    @Override
    public String getName() {
        return this.origin;
    }
}
