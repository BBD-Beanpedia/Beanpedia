package net.ryan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CliOptionHelper {
    public static CliOptionHelper register() {
        return new CliOptionHelper(List.of(
                new CliViewOption()
        ));
    }


    public CliOptionHelper(List<CliOption> options) {
//        options.forEach(option -> map.put());
    }

    public void show() {

    }
}
