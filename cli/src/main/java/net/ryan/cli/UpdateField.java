package net.ryan.cli;

public enum UpdateField implements Nameable {
    NAME("Update Bean Name"), SCIENTIFIC_NAME("Update Scientific Name"), CONTENT("Update Content"), ORIGIN("Update Origin"), TYPE("Update Type"), SHAPE("Update Shape"), COLOUR("Update Colour"), FINISH("Finish Update");

    private final String name;

    UpdateField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
