package net.ryan.cli;

public enum PaginationField implements Nameable {
    MENU("Menu"), NEXT("next"),

    PREV("prev");

    private final String name;

    PaginationField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
