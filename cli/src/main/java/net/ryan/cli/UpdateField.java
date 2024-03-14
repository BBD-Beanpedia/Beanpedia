package net.ryan.cli;

import net.ryan.util.MapUtils;

import java.util.Arrays;
import java.util.Map;

public enum UpdateField implements Nameable {
    NAME("Bean Name"), SCIENTIFIC_NAME("Scientific Name"), CONTENT("Content"), ORIGIN("Origin"), TYPE("Type"), SHAPE("Shape"), COLOUR("Colour"), FINISH("Finish Update");

    private final String name;

    UpdateField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Map<Integer, UpdateField> createMap() {
        return MapUtils.listToMap(Arrays.stream(UpdateField.values())
                                        .toList());
    }

}
