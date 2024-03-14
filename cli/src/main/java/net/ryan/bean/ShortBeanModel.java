package net.ryan.bean;

import net.ryan.cli.Nameable;

public record ShortBeanModel(int beanId, String beanName) implements Nameable {
    @Override
    public String getName() {
        return beanName;
    }
}
