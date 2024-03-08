package net.ryan.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BeanJsonMapper {
   /* public static ShortBeanModel parseStringToShortBeanModel(String inputJson) {

    }*/


    public static Map<String, String> parseJson(String jsonString) {
        Map<String, String> keyValueMap = new HashMap<>();
        Pattern pattern = Pattern.compile("\"(\\w+)\":\\s*\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(jsonString);
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            keyValueMap.put(key, value);
        }
        return keyValueMap;
    }
}
