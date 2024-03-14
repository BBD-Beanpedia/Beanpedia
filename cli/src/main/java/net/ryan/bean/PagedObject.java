package net.ryan.bean;

import net.ryan.util.MapUtils;

import java.util.List;
import java.util.Map;

public record PagedObject<T>(List<T> itemsList, Integer totalPages) {
    public Map<Integer, T> createBeanModelMap() {
        return MapUtils.listToMap(itemsList);
    }
}
