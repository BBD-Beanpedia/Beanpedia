package net.ryan.cli;

import net.ryan.util.MapUtils;

import java.util.Arrays;
import java.util.Map;

public enum UpdateField implements Nameable {
    NAME("Update Bean Name"), SCIENTIFIC_NAME("Update Scientific Name"), CONTENT("Update Content"), ORIGIN("Update Origin"), TYPE("Update Type"), SHAPE("Update Shape"), COLOUR("Update Colour"), FINISH("Finish Update");

    private final String name;

    UpdateField(String name) {
        this.name = name;
    }

    public static Map<Integer, UpdateField> createMap() {
        return MapUtils.listToMap(Arrays.stream(UpdateField.values())
                                        .toList());
    }

    @Override
    public String getName() {
        return name;
    }


}
