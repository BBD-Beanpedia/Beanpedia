package net.ryan.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MapUtils {
    public static <T> Map<Integer, T> listToMap(List<T> list) {
        return IntStream.range(0, list.size()).boxed().collect(Collectors.toMap(i -> i, list::get));
    }
}
