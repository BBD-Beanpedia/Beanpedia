package net.ryan.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapUtilsTest {

    @Test
    void testListToMap() {
        List<String> list = Arrays.asList("bean 1", "bean 2", "bean 3");
        Map<Integer, String> map = MapUtils.listToMap(list);

        assertEquals(3, map.size());
        assertEquals("bean 1", map.get(0));
        assertEquals("bean 2", map.get(1));
        assertEquals("bean 3", map.get(2));
    }
}
