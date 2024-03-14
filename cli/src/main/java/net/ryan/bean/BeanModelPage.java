package net.ryan.bean;


import net.ryan.util.MapUtils;

import java.util.List;
import java.util.Map;

public record BeanModelPage(List<BeanModelFull> beanList, Integer maxPages) {

    public Map<Integer, BeanModelFull> createBeanModelMap() {
        return MapUtils.listToMap(beanList);
    }
}
