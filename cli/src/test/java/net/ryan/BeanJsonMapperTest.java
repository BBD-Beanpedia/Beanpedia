package net.ryan;

import net.ryan.bean.BeanJsonMapper;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class BeanJsonMapperTest {
    @Test
    void parse_json() {
        Map<String, String> stringStringMap = BeanJsonMapper.parseJson("""
                [{
                    "Test": "wow"
                },
                {
                    "Test": "wow1"
                },
                ]
                """);

        System.out.println(stringStringMap);
    }
}
