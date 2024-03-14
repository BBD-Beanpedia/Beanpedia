package net.ryan;

import net.ryan.cli.options.*;
import net.ryan.util.InputUtils;
import net.ryan.util.MapUtils;

import java.util.List;
import java.util.Map;

public class CliOptionHelper {

    private static CliOptionHelper instance;

    public static CliOptionHelper getInstance() {
        if (instance == null) instance = register();
        return instance;
    }

    private final Map<Integer, CliOption> cliOptions;

    public static CliOptionHelper register() {
        List<CliOption> options = List.of(new CliViewOption(), new CliSearchOption(), new CliFilterOption(), new CliCreateOption(), new CliAuthOption(), new CliExitOption());
        return new CliOptionHelper(MapUtils.listToMap(options));
    }


    public CliOptionHelper(Map<Integer, CliOption> options) {
        this.cliOptions = options;
    }

    public void show() {
        System.out.println("Select an option\n");
        cliOptions.forEach((integer, cliOption) -> System.out.printf("\t%d. %s\n", integer + 1, cliOption.getName()));
        System.out.println("\nEnter a number to run an option: ");
        getInputInRange();
    }

    public void getInputInRange() {
        InputUtils.getInstance()
                  .readMapChoiceRangeFromConsole(cliOptions)
                  .ifSuccess(CliOption::run)
                  .ifError(error -> {
                      System.out.println("Error: Unrecognized command " + error.getMessage());
                      show();
                  });
    }


}
