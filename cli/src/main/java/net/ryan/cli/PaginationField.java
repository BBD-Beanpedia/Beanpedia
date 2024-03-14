package net.ryan.cli;

import net.ryan.util.MapUtils;

import java.util.Arrays;
import java.util.Map;

public enum PaginationField implements Nameable {

    NEXT("next"), PREVIOUS("prev"), MENU("menu");
    private final String name;

    PaginationField(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public static Map<Integer, PaginationField> createMap() {
        return MapUtils.listToMap(Arrays.stream(PaginationField.values())
                                        .toList());
    }

}
